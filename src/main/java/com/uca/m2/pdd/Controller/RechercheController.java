package com.uca.m2.pdd.Controller;

import com.uca.m2.pdd.Model.dto.RechercheDto;
import com.uca.m2.pdd.Model.entity.Recherche;
import com.uca.m2.pdd.Service.RechercheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/recherche")
public class RechercheController {
    @Autowired
    private RechercheService rechercheService;

    @GetMapping("/{id}")
    public RechercheDto getRechercheById(@PathVariable UUID id) {
        return rechercheService.getRechercheById(id);
    }

    @PostMapping
    public Recherche saveRecherche(@RequestBody Recherche recherche) {
        return rechercheService.saveRecherche(recherche);
    }

    @DeleteMapping("/{id}")
    public void deleteRecherche(@PathVariable UUID id) {
        rechercheService.deleteRecherche(id);
    }
}
