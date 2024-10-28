package com.uca.m2.pdd.Repository;

import com.uca.m2.pdd.Model.entity.Recherche;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RechercheRepository extends JpaRepository<Recherche, UUID> {
}
