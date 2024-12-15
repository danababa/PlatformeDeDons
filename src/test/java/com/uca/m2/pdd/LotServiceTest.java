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
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
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
        UUID userId = UUID.randomUUID();
        LotDto lotDto = new LotDto();
        lotDto.setTitre("Nouveau Lot");
        lotDto.setDescription("Un lot de test");

        Mockito.when(usersRepository.existsById(userId)).thenReturn(true);

        Lot savedLot = new Lot();
        savedLot.setId(UUID.randomUUID());
        savedLot.setUserId(userId);
        Mockito.when(lotRepository.save(Mockito.any())).thenReturn(savedLot);

        LotDto result = lotService.createLot(userId, lotDto);

        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals("Nouveau Lot", result.getTitre());
    }

    @Test
    void testGetLotsByUser_Success() {
        UUID userId = UUID.randomUUID();
        List<Lot> lots = List.of(new Lot(), new Lot());
        Mockito.when(lotRepository.findByUserId(userId)).thenReturn(lots);

        List<LotDto> result = lotService.getLotsByUser(userId);

        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testDeleteLot_Success() {
        UUID userId = UUID.randomUUID();
        UUID lotId = UUID.randomUUID();

        Lot lot = new Lot();
        lot.setId(lotId);
        lot.setUserId(userId);

        Mockito.when(lotRepository.findById(lotId)).thenReturn(Optional.of(lot));

        Assertions.assertDoesNotThrow(() -> lotService.deleteLot(userId, lotId));
    }

    @Test
    void testDeleteLot_NotOwnedByUser() {
        UUID userId = UUID.randomUUID();
        UUID lotId = UUID.randomUUID();

        Lot lot = new Lot();
        lot.setId(lotId);
        lot.setUserId(UUID.randomUUID());

        Mockito.when(lotRepository.findById(lotId)).thenReturn(Optional.of(lot));

        Assertions.assertThrows(IllegalArgumentException.class, () -> lotService.deleteLot(userId, lotId));
    }
}
