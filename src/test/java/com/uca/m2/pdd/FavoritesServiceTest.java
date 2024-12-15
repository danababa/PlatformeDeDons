package com.uca.m2.pdd;

import com.uca.m2.pdd.Configuration.FavoritesId;
import com.uca.m2.pdd.Mapper.FavoritesMapper;
import com.uca.m2.pdd.Model.dto.FavoritesDto;
import com.uca.m2.pdd.Model.entity.Favorites;
import com.uca.m2.pdd.Repository.FavoritesRepository;
import com.uca.m2.pdd.Service.FavoritesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FavoritesServiceTest {

    @Mock
    private FavoritesRepository favoritesRepository;

    @InjectMocks
    private FavoritesService favoritesService;

    @Test
    void testCreateFavorites_Success() {
        UUID userId = UUID.randomUUID();
        UUID annonceId = UUID.randomUUID();

        FavoritesDto favoritesDto = new FavoritesDto();
        favoritesDto.setUserId(userId);
        favoritesDto.setAnnonceId(annonceId);
        favoritesDto.setDateAjouter(new Date().toInstant().toString());

        Favorites favorites = FavoritesMapper.toFavoritesEntity(favoritesDto);
        Mockito.when(favoritesRepository.existsById(favorites.getId())).thenReturn(false);
        Mockito.when(favoritesRepository.save(Mockito.any())).thenReturn(favorites);

        FavoritesDto result = favoritesService.createFavorites(favoritesDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(userId, result.getUserId());
        Assertions.assertEquals(annonceId, result.getAnnonceId());
    }

    @Test
    void testCreateFavorites_AlreadyExists() {
        UUID userId = UUID.randomUUID();
        UUID annonceId = UUID.randomUUID();

        FavoritesDto favoritesDto = new FavoritesDto();
        favoritesDto.setUserId(userId);
        favoritesDto.setAnnonceId(annonceId);

        Favorites favorites = FavoritesMapper.toFavoritesEntity(favoritesDto);
        Mockito.when(favoritesRepository.existsById(favorites.getId())).thenReturn(true);

        Assertions.assertThrows(IllegalStateException.class, () -> {
            favoritesService.createFavorites(favoritesDto);
        });
    }

    @Test
    void testGetFavoritesById_Success() {
        UUID userId = UUID.randomUUID();
        UUID annonceId = UUID.randomUUID();

        FavoritesId favoritesId = new FavoritesId(userId, annonceId);
        Favorites favorites = new Favorites();
        favorites.setId(favoritesId);
        favorites.setDateAjouter(new Date());

        Mockito.when(favoritesRepository.findById(favoritesId)).thenReturn(Optional.of(favorites));

        FavoritesDto result = favoritesService.getFavoritesById(userId, annonceId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(userId, result.getUserId());
        Assertions.assertEquals(annonceId, result.getAnnonceId());
    }

    @Test
    void testGetFavoritesById_NotFound() {
        UUID userId = UUID.randomUUID();
        UUID annonceId = UUID.randomUUID();

        FavoritesId favoritesId = new FavoritesId(userId, annonceId);

        Mockito.when(favoritesRepository.findById(favoritesId)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalStateException.class, () -> {
            favoritesService.getFavoritesById(userId, annonceId);
        });
    }

    @Test
    void testGetFavoritesByUser_Success() {
        UUID userId = UUID.randomUUID();
        List<Favorites> favoritesList = new ArrayList<>();
        Favorites favorites = new Favorites();
        FavoritesId favoritesId = new FavoritesId(userId, UUID.randomUUID());
        favorites.setId(favoritesId);
        favorites.setDateAjouter(new Date());
        favoritesList.add(favorites);

        Mockito.when(favoritesRepository.findAllById_UserId(userId)).thenReturn(favoritesList);

        List<FavoritesDto> result = favoritesService.getFavoritesByUser(userId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(userId, result.get(0).getUserId());
    }

    @Test
    void testUpdateFavorites_Success() {
        UUID userId = UUID.randomUUID();
        UUID annonceId = UUID.randomUUID();

        FavoritesDto favoritesDto = new FavoritesDto();
        favoritesDto.setUserId(userId);
        favoritesDto.setAnnonceId(annonceId);
        favoritesDto.setDateAjouter(new Date().toInstant().toString());

        Favorites favorites = FavoritesMapper.toFavoritesEntity(favoritesDto);
        FavoritesId favoritesId = new FavoritesId(userId, annonceId);

        Mockito.when(favoritesRepository.existsById(favoritesId)).thenReturn(true);
        Mockito.when(favoritesRepository.save(Mockito.any())).thenReturn(favorites);

        FavoritesDto result = favoritesService.updateFavorites(favoritesDto, userId, annonceId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(userId, result.getUserId());
        Assertions.assertEquals(annonceId, result.getAnnonceId());
    }

    @Test
    void testUpdateFavorites_NotFound() {
        UUID userId = UUID.randomUUID();
        UUID annonceId = UUID.randomUUID();

        FavoritesDto favoritesDto = new FavoritesDto();
        favoritesDto.setUserId(userId);
        favoritesDto.setAnnonceId(annonceId);

        FavoritesId favoritesId = new FavoritesId(userId, annonceId);

        Mockito.when(favoritesRepository.existsById(favoritesId)).thenReturn(false);

        Assertions.assertThrows(IllegalStateException.class, () -> {
            favoritesService.updateFavorites(favoritesDto, userId, annonceId);
        });
    }

    @Test
    void testDeleteFavorites_Success() {
        UUID userId = UUID.randomUUID();
        UUID annonceId = UUID.randomUUID();

        FavoritesId favoritesId = new FavoritesId(userId, annonceId);

        Mockito.when(favoritesRepository.existsById(favoritesId)).thenReturn(true);

        Assertions.assertDoesNotThrow(() -> favoritesService.deleteFavorites(userId, annonceId));
        verify(favoritesRepository, times(1)).deleteById(favoritesId);
    }

    @Test
    void testDeleteFavorites_NotFound() {
        UUID userId = UUID.randomUUID();
        UUID annonceId = UUID.randomUUID();

        FavoritesId favoritesId = new FavoritesId(userId, annonceId);

        Mockito.when(favoritesRepository.existsById(favoritesId)).thenReturn(false);

        Assertions.assertThrows(IllegalStateException.class, () -> favoritesService.deleteFavorites(userId, annonceId));
    }
}
