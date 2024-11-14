package com.uca.m2.pdd.Service;

import com.uca.m2.pdd.Mapper.FavoritesMapper;
import com.uca.m2.pdd.Model.dto.FavoritesDto;
import com.uca.m2.pdd.Model.entity.Favorites;
import com.uca.m2.pdd.Repository.FavoritesRepository;
import org.springframework.stereotype.Service;
import com.uca.m2.pdd.Configuration.FavoritesId;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FavoritesService {
    private final FavoritesRepository FavoritesRepository;

    private FavoritesService(FavoritesRepository FavoritesRepository) {
        this.FavoritesRepository = FavoritesRepository;
    }

    /**
     * Create a new Favorite.
     *
     * @param favoritesDto Favorites DTO.
     * @return The created Favorite as a DTO.
     */
    public FavoritesDto createFavorites(FavoritesDto favoritesDto) {
        Favorites favorites = FavoritesMapper.toFavoritesEntity(favoritesDto);
        if (FavoritesRepository.existsById(favorites.getId())) {
            throw new IllegalStateException("Favorite already exists");
        }
        Favorites savedFavorite = FavoritesRepository.save(favorites);
        return FavoritesMapper.toFavoritesDto(savedFavorite);
    }

    /**
     * Get a Favorite by its composite ID.
     *
     * @param userId    The user's UUID.
     * @param annonceId The annonce's UUID.
     * @return The Favorite as a DTO.
     */
    public FavoritesDto getFavoritesById(UUID userId, UUID annonceId) {
        FavoritesId favoritesId = new FavoritesId(userId, annonceId);
        Favorites favorite = FavoritesRepository.findById(favoritesId)
                .orElseThrow(() -> new IllegalStateException("Favorite not found"));
        return FavoritesMapper.toFavoritesDto(favorite);
    }

    public List<FavoritesDto> getFavoritesByUser(UUID userId) {
        List<Favorites> favoritesList = FavoritesRepository.findAllById_UserId(userId);
        return favoritesList.stream()
                .map(FavoritesMapper::toFavoritesDto)
                .collect(Collectors.toList());
    }


    /**
     * Update a Favorite.
     *
     * @param favoritesDto Favorites DTO containing the new data.
     * @param userId       The user's UUID.
     * @param annonceId    The annonce's UUID.
     * @return The updated Favorite as a DTO.
     */
    public FavoritesDto updateFavorites(FavoritesDto favoritesDto, UUID userId, UUID annonceId) {
        FavoritesId favoritesId = new FavoritesId(userId, annonceId);

        if (!FavoritesRepository.existsById(favoritesId)) {
            throw new IllegalStateException("Favorite not found");
        }

        Favorites favorites = FavoritesMapper.toFavoritesEntity(favoritesDto);
        favorites.setId(favoritesId); // Ensure the correct ID is set
        Favorites updatedFavorite = FavoritesRepository.save(favorites);
        return FavoritesMapper.toFavoritesDto(updatedFavorite);
    }

    /**
     * Delete a Favorite by its composite ID.
     *
     * @param userId    The user's UUID.
     * @param annonceId The annonce's UUID.
     */
    public void deleteFavorites(UUID userId, UUID annonceId) {
        FavoritesId favoritesId = new FavoritesId(userId, annonceId);

        if (!FavoritesRepository.existsById(favoritesId)) {
            throw new IllegalStateException("Favorite not found");
        }

        FavoritesRepository.deleteById(favoritesId);
    }
}
