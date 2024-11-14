package com.uca.m2.pdd.Repository;

import com.uca.m2.pdd.Configuration.FavoritesId;
import com.uca.m2.pdd.Model.entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FavoritesRepository extends JpaRepository<Favorites, FavoritesId> {
    List<Favorites> findAllById_UserId(UUID userId);
}
