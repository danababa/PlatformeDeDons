package com.uca.m2.pdd.Controller;

import com.uca.m2.pdd.Model.dto.FavoritesDto;
import com.uca.m2.pdd.Service.FavoritesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/Favorites")
public class FavoritesController {
    private final FavoritesService FavoritesService;

    /**
     * Create a new favorite.
     *
     * @param favoritesDto .
     * @return new favorite.
     */
    @PostMapping
    public ResponseEntity<FavoritesDto> createFavorite(@RequestBody FavoritesDto favoritesDto) {
        FavoritesDto createdFavorite = FavoritesService.createFavorites(favoritesDto);
        return ResponseEntity.ok(createdFavorite);
    }

    /**
     * Get a favorite by user ID and annonce ID.
     *
     * @param userId    The UUID of the user.
     * @param annonceId The UUID of the annonce.
     * @return The found favorite.
     */
    @GetMapping("/{userId}/{annonceId}")
    public ResponseEntity<FavoritesDto> getFavorite(
            @PathVariable UUID userId,
            @PathVariable UUID annonceId
    ) {
        FavoritesDto favorite = FavoritesService.getFavoritesById(userId, annonceId);
        return ResponseEntity.ok(favorite);
    }

    /**
     * Get all favorites of a user by user ID.
     *
     * @param userId    The UUID of the user.
     * @return The list of favorites.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoritesDto>> getFavoritesByUser(@PathVariable UUID userId) {
        List<FavoritesDto> favorites = FavoritesService.getFavoritesByUser(userId);
        return ResponseEntity.ok(favorites);
    }


    /**
     * Update an existing favorite.
     *
     * @param userId       The UUID of the user.
     * @param annonceId    The UUID of the annonce.
     * @param favoritesDto The updated details for the favorite.
     * @return The updated favorite.
     */
    @PutMapping("/{userId}/{annonceId}")
    public ResponseEntity<FavoritesDto> updateFavorite(
            @PathVariable UUID userId,
            @PathVariable UUID annonceId,
            @RequestBody FavoritesDto favoritesDto
    ) {
        FavoritesDto updatedFavorite = FavoritesService.updateFavorites(favoritesDto, userId, annonceId);
        return ResponseEntity.ok(updatedFavorite);
    }

    /**
     * Delete a favorite by user ID and annonce ID.
     *
     * @param userId    The UUID of the user.
     * @param annonceId The UUID of the annonce.
     * @return A response indicating the deletion was successful.
     */
    @DeleteMapping("/{userId}/{annonceId}")
    public ResponseEntity<Void> deleteFavorite(
            @PathVariable UUID userId,
            @PathVariable UUID annonceId
    ) {
        FavoritesService.deleteFavorites(userId, annonceId);
        return ResponseEntity.noContent().build();
    }
}
