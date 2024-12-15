package com.uca.m2.pdd.Model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "filters")
public class Filter {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID userId; // Clé étrangère vers l'utilisateur

    @Column
    private String etat;

    @Column
    private String modeDeRemise;

    @ElementCollection
    private List<String> motsCles;

    @Column
    private LocalDate dateDePublication;
}
