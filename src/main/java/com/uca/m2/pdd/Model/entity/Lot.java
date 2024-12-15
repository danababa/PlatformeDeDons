package com.uca.m2.pdd.Model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Entité représentant un lot d'annonces.
 * Un lot appartient à un utilisateur et contient plusieurs annonces regroupées.
 */
@Entity
@Data
@Table(name = "lots")
public class Lot {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID userId; // L'utilisateur propriétaire du lot

    @Column(nullable = false)
    private String titre;

    @Column
    private String description;

    @Column(nullable = false)
    private LocalDate dateCreation = LocalDate.now();

    @ElementCollection
    @CollectionTable(name="lot_annonces", joinColumns=@JoinColumn(name="lot_id"))
    @Column(name="annonce_id")
    private List<UUID> annoncesIds;
    // On stocke simplement les IDs des annonces.
    // Vous pourrez ensuite récupérer les entités annonces dans le service.
}
