package com.uca.m2.pdd;

import com.uca.m2.pdd.Model.dto.LotDto;
import com.uca.m2.pdd.Model.entity.Lot;
import com.uca.m2.pdd.Repository.LotRepository;
import com.uca.m2.pdd.Repository.UsersRepository;
import com.uca.m2.pdd.Service.LotService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class LotServiceTest {

    @Mock
    private LotRepository lotRepository;

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private LotService lotService;

    @Test
    void testCreateLot_Success() {
        // Arrange
        UUID userId = UUID.randomUUID();
        LotDto lotDto = new LotDto();
        lotDto.setTitre("Nouveau Lot");
        lotDto.setDescription("Un lot de test");

        Mockito.when(usersRepository.existsById(userId)).thenReturn(true);

        Lot savedLot = new Lot();
        savedLot.setId(UUID.randomUUID());
        savedLot.setUserId(userId);
        savedLot.setTitre("Nouveau Lot");
        savedLot.setDescription("Un lot de test");

        Mockito.when(lotRepository.save(Mockito.any())).thenReturn(savedLot);

        // Act
        LotDto result = lotService.createLot(userId, lotDto);

        // Assert
        Assertions.assertNotNull(result.getId(), "Le lot créé doit avoir un ID");
        Assertions.assertEquals("Nouveau Lot", result.getTitre(), "Le titre du lot doit correspondre");
        Assertions.assertEquals("Un lot de test", result.getDescription(), "La description du lot doit correspondre");
    }

    @Test
    void testCreateLot_UserNotFound() {
        // Arrange
        UUID userId = UUID.randomUUID();
        LotDto lotDto = new LotDto();

        Mockito.when(usersRepository.existsById(userId)).thenReturn(false);

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> lotService.createLot(userId, lotDto),
                "Une exception doit être levée si l'utilisateur n'existe pas");
    }

    @Test
    void testGetLotsByUser_Success() {
        // Arrange
        UUID userId = UUID.randomUUID();
        Lot lot1 = new Lot();
        lot1.setId(UUID.randomUUID());
        lot1.setUserId(userId);
        lot1.setTitre("Lot 1");

        Lot lot2 = new Lot();
        lot2.setId(UUID.randomUUID());
        lot2.setUserId(userId);
        lot2.setTitre("Lot 2");

        Mockito.when(lotRepository.findByUserId(userId)).thenReturn(List.of(lot1, lot2));

        // Act
        List<LotDto> result = lotService.getLotsByUser(userId);

        // Assert
        Assertions.assertEquals(2, result.size(), "Le nombre de lots doit correspondre");
        Assertions.assertEquals("Lot 1", result.get(0).getTitre(), "Le titre du premier lot doit correspondre");
        Assertions.assertEquals("Lot 2", result.get(1).getTitre(), "Le titre du deuxième lot doit correspondre");
    }

    @Test
    void testDeleteLot_Success() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UUID lotId = UUID.randomUUID();

        Lot lot = new Lot();
        lot.setId(lotId);
        lot.setUserId(userId);

        Mockito.when(lotRepository.findById(lotId)).thenReturn(Optional.of(lot));

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> lotService.deleteLot(userId, lotId),
                "Aucune exception ne doit être levée si le lot appartient à l'utilisateur");
        Mockito.verify(lotRepository, Mockito.times(1)).delete(lot);
    }

    @Test
    void testDeleteLot_NotOwnedByUser() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UUID lotId = UUID.randomUUID();

        Lot lot = new Lot();
        lot.setId(lotId);
        lot.setUserId(UUID.randomUUID()); // Propriétaire différent

        Mockito.when(lotRepository.findById(lotId)).thenReturn(Optional.of(lot));

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> lotService.deleteLot(userId, lotId),
                "Une exception doit être levée si le lot n'appartient pas à l'utilisateur");
    }

    @Test
    void testDeleteLot_NotFound() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UUID lotId = UUID.randomUUID();

        Mockito.when(lotRepository.findById(lotId)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> lotService.deleteLot(userId, lotId),
                "Une exception doit être levée si le lot n'existe pas");
    }
}
