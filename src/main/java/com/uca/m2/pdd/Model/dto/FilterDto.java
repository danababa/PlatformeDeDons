package com.uca.m2.pdd.Model.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class FilterDto {
    private UUID id;
    private UUID userId; // Optionnel si vous souhaitez le set côté backend
    private String etat;
    private String modeDeRemise;
    private List<String> motsCles;
    private LocalDate dateDePublication;

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
    public String getEtat() {
        return etat;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }
    public String getModeDeRemise() {
        return modeDeRemise;
    }
    public void setModeDeRemise(String modeDeRemise) {
        this.modeDeRemise = modeDeRemise;
    }
    public List<String> getMotsCles() {
        return motsCles;
    }
    public void setMotsCles(List<String> motsCles) {
        this.motsCles = motsCles;
    }
    public LocalDate getDateDePublication() {
        return dateDePublication;
    }
    public void setDateDePublication(LocalDate dateDePublication) {
        this.dateDePublication = dateDePublication;
    }
}
