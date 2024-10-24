package com.uca.m2.pdd.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data //Annotation to generate getters and setters
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "numeroTelephone")
    private String numeroTelephone;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;
}
