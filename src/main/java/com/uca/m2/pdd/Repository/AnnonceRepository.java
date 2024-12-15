package com.uca.m2.pdd.Repository;

import com.uca.m2.pdd.Model.Enum.ModeDeRemiseEnum;
import com.uca.m2.pdd.Model.entity.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Repository pour gérer les opérations CRUD sur les annonces.
 */
public interface AnnonceRepository extends JpaRepository<Annonce, UUID> {

    /**
     * Trouve les annonces par mode de remise.
     *
     * @param modeDeRemise Mode de remise (EN_MAIN_PROPRE ou ENVOI)
     * @return Liste d'annonces correspondant au mode de remise spécifié
     */
    List<Annonce> findByModeDeRemiseEnum(ModeDeRemiseEnum modeDeRemise);

    /**
     * Recherche des annonces en fonction de différents critères optionnels.
     *
     * @param etat              État de l'objet (optionnel)
     * @param modeDeRemise      Valeur de l'enum ModeDeRemiseEnum (optionnel)
     * @param motsCles          Liste de mots-clés (optionnel)
     * @param dateDePublication Date de publication minimum (optionnel)
     * @return Liste d'annonces correspondant aux critères
     */
    @Query("SELECT DISTINCT a FROM Annonce a " +
            "LEFT JOIN a.motsCles m " +
            "WHERE (:etat IS NULL OR a.etat = :etat) " +
            "AND (:modeDeRemise IS NULL OR a.modeDeRemiseEnum = :modeDeRemise) " +
            "AND (:dateDePublication IS NULL OR a.datePublication >= :dateDePublication) " +
            "AND (:motsCles IS NULL OR m IN :motsCles)")
    List<Annonce> searchAnnonces(
            @Param("etat") String etat,
            @Param("modeDeRemise") ModeDeRemiseEnum modeDeRemise,
            @Param("motsCles") List<String> motsCles,
            @Param("dateDePublication") LocalDate dateDePublication
    );
}

