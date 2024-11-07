package com.uca.m2.pdd.Repository;

import com.uca.m2.pdd.Model.entity.Annonces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnnoncesRepository extends JpaRepository<Annonces, UUID> {
}
