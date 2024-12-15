package com.uca.m2.pdd.Repository;

import com.uca.m2.pdd.Model.entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository pour gérer les opérations CRUD sur les lots.
 */
@Repository
public interface LotRepository extends JpaRepository<Lot, UUID> {
    List<Lot> findByUserId(UUID userId);
}
