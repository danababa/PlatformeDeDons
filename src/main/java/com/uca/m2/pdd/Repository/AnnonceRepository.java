package com.uca.m2.pdd.Repository;

import com.uca.m2.pdd.Model.entity.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnnonceRepository extends JpaRepository<Annonce, UUID> {
}
