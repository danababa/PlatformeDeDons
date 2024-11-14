package com.uca.m2.pdd.Repository;

import com.uca.m2.pdd.Model.Enum.ModeDeRemiseEnum;
import com.uca.m2.pdd.Model.entity.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnnonceRepository extends JpaRepository<Annonce, UUID> {
    Optional<Object> findByTitre(String titre);
    @Query("SELECT a FROM Annonce a WHERE a.modeDeRemiseEnum = :modeDeRemise")
    List<Annonce> findByModeDeRemise(@Param("modeDeRemise") ModeDeRemiseEnum modeDeRemise);
}
