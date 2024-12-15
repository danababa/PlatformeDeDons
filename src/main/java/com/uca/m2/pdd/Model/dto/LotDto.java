package com.uca.m2.pdd.Model.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * DTO pour représenter un lot.
 */
public class LotDto {
    private UUID id;
    private UUID userId;
    private String titre;
    private String description;
    private LocalDate dateCreation;
    private List<UUID> annoncesIds; // Liste des ID des annonces dans le lot

    // Getters et Setters
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }
    public List<UUID> getAnnoncesIds() {
        return annoncesIds;
    }
    public void setAnnoncesIds(List<UUID> annoncesIds) {
        this.annoncesIds = annoncesIds;
    }
}

