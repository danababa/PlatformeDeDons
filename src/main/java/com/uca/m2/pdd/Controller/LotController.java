package com.uca.m2.pdd.Controller;

import com.uca.m2.pdd.Model.dto.LotDto;
import com.uca.m2.pdd.Service.LotService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Contrôleur REST pour gérer les lots d'annonces.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/users/{userId}/lots")
public class LotController {

    private final LotService lotService;

    /**
     * Crée un nouveau lot pour un utilisateur.
     * @param userId ID de l'utilisateur
     * @param lotDto Données du lot
     * @return Le lot créé sous forme de LotDto
     */
    @PostMapping
    public ResponseEntity<LotDto> createLot(@PathVariable UUID userId, @RequestBody LotDto lotDto) {
        return ResponseEntity.ok(lotService.createLot(userId, lotDto));
    }

    /**
     * Récupère tous les lots d'un utilisateur.
     * @param userId ID de l'utilisateur
     * @return Liste de LotDto
     */
    @GetMapping
    public ResponseEntity<List<LotDto>> getLotsByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(lotService.getLotsByUser(userId));
    }

    /**
     * Supprime un lot appartenant à un utilisateur.
     * @param userId ID de l'utilisateur
     * @param lotId ID du lot
     * @return Réponse vide (204 No Content)
     */
    @DeleteMapping("/{lotId}")
    public ResponseEntity<Void> deleteLot(@PathVariable UUID userId, @PathVariable UUID lotId) {
        lotService.deleteLot(userId, lotId);
        return ResponseEntity.noContent().build();
    }
}
