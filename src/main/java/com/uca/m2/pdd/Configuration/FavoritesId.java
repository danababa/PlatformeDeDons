package com.uca.m2.pdd.Configuration;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Embeddable;

/**
 * Method to create a composite key for the Favorites table
 * so that we can store the user's favorite ads
 * using the user's id and the ad's id at the same time
 */
@Embeddable
public class FavoritesId implements Serializable {

    private UUID userId;
    private UUID annoncesId;

    // Constructor with fields
    public FavoritesId(UUID userId, UUID annoncesId) {
        this.userId = userId;
        this.annoncesId = annoncesId;
    }

    public FavoritesId() {
    }

    // Override equals and hashCode for composite key comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritesId that = (FavoritesId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(annoncesId, that.annoncesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, annoncesId);
    }
}

