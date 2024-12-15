package com.uca.m2.pdd.Model.entity;

import com.uca.m2.pdd.Model.entity.Users;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "recherche")
public class Recherche {

    @Id
    @GeneratedValue
    private UUID id;

    @ElementCollection
    @Column(name = "motsCles")
    private List<String> motsCles;

    @Column(name = "userId")
    private UUID userId;
}
