package com.uca.m2.pdd.Controller;

import com.uca.m2.pdd.Model.Enum.ModeDeRemiseEnum;
import com.uca.m2.pdd.Model.dto.AnnonceDto;
import com.uca.m2.pdd.Service.AnnonceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Contrôleur REST pour gérer les annonces.
 */
@AllArgsConstructor
@RestController
@RequestMapping("annonces")
public class AnnonceController {
    private final AnnonceService annonceService;

    /**
     * Crée une nouvelle annonce.
     * @param annonceDto Corps de la requête représentant l'annonce à créer
     * @return L'annonce créée sous forme d'AnnonceDto
     */
    @PostMapping("")
    public ResponseEntity<AnnonceDto> createAnnonce(@RequestBody @Valid AnnonceDto annonceDto) {
        return ResponseEntity.ok().body(annonceService.createAnnonce(annonceDto));
    }

    /**
     * Récupère une annonce par son identifiant.
     * @param id Identifiant de l'annonce
     * @return L'annonce correspondante sous forme d'AnnonceDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<AnnonceDto> getAnnonceById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(annonceService.getAnnonceById(id));
    }

    /**
     * Met à jour une annonce existante.
     * @param id Identifiant de l'annonce à mettre à jour
     * @param annonceDto Corps de la requête représentant les nouvelles données
     * @return L'annonce mise à jour sous forme d'AnnonceDto
     */
    @PutMapping("/{id}")
    public ResponseEntity<AnnonceDto> updateAnnonce(@PathVariable UUID id, @RequestBody @Valid AnnonceDto annonceDto) {
        return ResponseEntity.ok().body(annonceService.updateAnnonce(annonceDto, id));
    }

    /**
     * Récupère toutes les annonces.
     * @return Liste d'AnnonceDto représentant toutes les annonces
     */
    @GetMapping("")
    public ResponseEntity<List<AnnonceDto>> getAllAnnonces() {
        return ResponseEntity.ok().body(annonceService.getAllAnnonces());
    }

    /**
     * Supprime une annonce par son identifiant.
     * @param id Identifiant de l'annonce à supprimer
     * @return Une réponse vide avec un statut HTTP 200 OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnonce(@PathVariable UUID id) {
        annonceService.deleteAnnonce(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Récupère toutes les annonces ayant un certain mode de remise.
     * @param modeDeRemise Le mode de remise (par exemple EN_MAIN_PROPRE ou ENVOI)
     * @return Liste d'AnnonceDto filtrées par le mode de remise
     */
    @GetMapping("/mode-de-remise/{modeDeRemise}")
    public ResponseEntity<List<AnnonceDto>> getAnnoncesByModeDeRemise(@PathVariable ModeDeRemiseEnum modeDeRemise) {
        return ResponseEntity.ok().body(annonceService.findAnnonceByModeDeRemise(modeDeRemise));
    }

    /**
     * Effectue une recherche avancée sur les annonces en fonction de plusieurs critères optionnels.
     * @param zoneGeographique Zone géographique (optionnel)
     * @param etat État de l'objet (optionnel)
     * @param modeDeRemise Mode de remise (optionnel, par ex. "EN_MAIN_PROPRE" ou "ENVOI")
     * @param motsCles Liste de mots-clés (optionnel)
     * @param dateDePublication Date de publication à partir de laquelle l'annonce doit avoir été publiée (optionnel)
     * @return Liste d'AnnonceDto correspondant aux critères de recherche
     */
    @GetMapping("/search")
    public ResponseEntity<List<AnnonceDto>> searchAnnonces(
            @RequestParam(required = false) String zoneGeographique,
            @RequestParam(required = false) String etat,
            @RequestParam(required = false) String modeDeRemise,
            @RequestParam(required = false) List<String> motsCles,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDePublication
    ) {
        List<AnnonceDto> annonces = annonceService.searchAnnonces(
                zoneGeographique, etat, modeDeRemise, motsCles, dateDePublication
        );
        return ResponseEntity.ok(annonces);
    }

}
