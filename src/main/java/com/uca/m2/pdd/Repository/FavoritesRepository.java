package com.uca.m2.pdd.Repository;

import com.uca.m2.pdd.Configuration.FavoritesId;
import com.uca.m2.pdd.Model.entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesRepository extends JpaRepository<Favorites, FavoritesId> {
}
