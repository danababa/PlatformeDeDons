package com.uca.m2.pdd.Model;

import com.uca.m2.pdd.Configuration.FavoritesId;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "favourites")
public class Favorites {
    @EmbeddedId
    @Column(name = "id", nullable = false)
    private FavoritesId id;

    @Column(name = "dateAjouter", nullable = false)
    private Date dateAjouter;
}
