package com.uca.m2.pdd.Model.entity;

import com.uca.m2.pdd.Model.Enum.ModeDeRemiseEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "annonces")
public class Annonce {
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


    @Enumerated(EnumType.STRING)
    @Column(name = "modeDeRemise")
    private ModeDeRemiseEnum modeDeRemiseEnum;

    @ElementCollection
    @Column(name = "motsCles")
    private List<String> motsCles;

    public Annonce(UUID id, String titre, String description, String etat, Date datePublication,
                   double longitude, double latitude, ModeDeRemiseEnum modeDeRemise, List<String> motsCles) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.etat = etat;
        this.datePublication = datePublication;
        this.longitude = longitude;
        this.latitude = latitude;
        this.modeDeRemiseEnum = modeDeRemise;
        this.motsCles = motsCles;
    }

    public Annonce() {
    }

}
