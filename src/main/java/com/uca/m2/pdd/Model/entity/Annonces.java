package com.uca.m2.pdd.Model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "annonces")
public class Annonces {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "titre", nullable = false)
    private String titre;

    @Column(name = "description")
    private String description;

    @Column(name = "etat")
    private String etat;

    @Column(name = "datePublication")
    private Date datePublication;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "modeDeRemise")
    private String modeDeRemise;

    @ElementCollection
    @Column(name = "motsCles")
    private List<String> motsCles;
}
