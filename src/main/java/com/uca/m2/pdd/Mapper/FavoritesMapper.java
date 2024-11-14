package com.uca.m2.pdd.Mapper;

import com.uca.m2.pdd.Configuration.FavoritesId;
import com.uca.m2.pdd.Model.dto.FavoritesDto;
import com.uca.m2.pdd.Model.entity.Favorites;

import java.util.Date;

public class FavoritesMapper {
    // Method to convert Favorites entity to FavoritesDto
    public static FavoritesDto toFavoritesDto(Favorites favorites) {
        if (favorites == null) {
            return null;
        }

        FavoritesDto favoritesDto = new FavoritesDto();
        FavoritesId id = favorites.getId(); // Get the embedded ID

        // Set userId and annoncesId from the composite key
        if (id != null) {
            favoritesDto.setUserId(id.getUserId());
            favoritesDto.setAnnonceId(id.getAnnoncesId());
        }

        // Convert Date to String for dateAjouter
        Date dateAjouter = favorites.getDateAjouter();
        favoritesDto.setDateAjouter(dateAjouter != null ? dateAjouter.toString() : null);

        return favoritesDto;
    }

    // Method to convert FavoritesDto to Favorites entity
    public static Favorites toFavoritesEntity(FavoritesDto favoritesDto) {
        if (favoritesDto == null) {
            return null;
        }

        Favorites favorites = new Favorites();

        // Create FavoritesId from userId and annoncesId
        FavoritesId favoritesId = new FavoritesId(favoritesDto.getUserId(), favoritesDto.getAnnonceId());
        favorites.setId(favoritesId);

        // Convert String to Date for dateAjouter (assuming a proper date format)
        try {
            Date date = Date.from(java.time.Instant.parse(favoritesDto.getDateAjouter()));
            favorites.setDateAjouter(date);
        } catch (Exception e) {
            // Handle parsing exception
            favorites.setDateAjouter(null); // Or handle as needed
        }

        return favorites;
    }
}
