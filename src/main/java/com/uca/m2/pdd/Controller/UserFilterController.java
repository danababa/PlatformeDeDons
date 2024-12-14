package com.uca.m2.pdd.Controller;

import com.uca.m2.pdd.Model.dto.FilterDto;
import com.uca.m2.pdd.Service.FilterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/users/{userId}/filters")
public class UserFilterController {

    private final FilterService filterService;

    /**
     * Crée un nouveau filtre pour un utilisateur.
     * @param userId ID de l'utilisateur
     * @param filterDto Détails du filtre
     * @return Le filtre créé
     */
    @PostMapping
    public ResponseEntity<FilterDto> createFilter(@PathVariable UUID userId, @RequestBody FilterDto filterDto) {
        return ResponseEntity.ok(filterService.createFilter(userId, filterDto));
    }

    /**
     * Récupère tous les filtres d'un utilisateur.
     * @param userId ID de l'utilisateur
     * @return Liste des filtres
     */
    @GetMapping
    public ResponseEntity<List<FilterDto>> getAllFiltersByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(filterService.getFiltersByUser(userId));
    }

    /**
     * Supprime un filtre d'un utilisateur.
     * @param userId ID de l'utilisateur
     * @param filterId ID du filtre
     * @return Réponse vide
     */
    @DeleteMapping("/{filterId}")
    public ResponseEntity<Void> deleteFilter(@PathVariable UUID userId, @PathVariable UUID filterId) {
        filterService.deleteFilter(userId, filterId);
        return ResponseEntity.noContent().build();
    }
}
