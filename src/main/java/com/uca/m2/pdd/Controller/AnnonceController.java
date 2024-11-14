package com.uca.m2.pdd.Controller;

import com.uca.m2.pdd.Model.Enum.ModeDeRemiseEnum;
import com.uca.m2.pdd.Model.dto.AnnonceDto;
import com.uca.m2.pdd.Service.AnnonceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("annonces")
public class AnnonceController {
    private final AnnonceService annonceService;

    /**
     * Create a new annonce
     * @param annonceDto body to create
     * @return created annonce body
     */
    @PostMapping("")
    public ResponseEntity<AnnonceDto> createAnnonce(@RequestBody @Valid AnnonceDto annonceDto) {
        return ResponseEntity.ok().body(annonceService.createAnnonce(annonceDto));
    }

    /**
     * Get annonce by id
     * @param id annonce id
     * @return annonce
     */
    @GetMapping("/{id}")
    public ResponseEntity<AnnonceDto> getAnnonceById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(annonceService.getAnnonceById(id));
    }

    /**
     * Update annonce
     * @param id annonce id
     * @param annonceDto body to update
     * @return updated annonce
     */
    @PutMapping("/{id}")
    public ResponseEntity<AnnonceDto> updateAnnonce(@PathVariable UUID id, @RequestBody @Valid AnnonceDto annonceDto) {
        return ResponseEntity.ok().body(annonceService.updateAnnonce(annonceDto, id));
    }


    /**
     * Get all annonces
     * @return List of all annonces
     */
    @GetMapping("")
    public ResponseEntity<List<AnnonceDto>> getAllAnnonces() {
        return ResponseEntity.ok().body(annonceService.getAllAnnonces());
    }

    /**
     * Delete annonce
     * @param id annonce id
     * @return response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnonce(@PathVariable UUID id) {
        annonceService.deleteAnnonce(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Get list of all annonces with a certain mode de remise
     * @param modeDeRemise mode de remise
     * @return list of annonces Dto
     */
    @GetMapping("/mode-de-remise/{modeDeRemise}")
    public ResponseEntity<List<AnnonceDto>> getAnnoncesByModeDeRemise(@PathVariable ModeDeRemiseEnum modeDeRemise) {
        return ResponseEntity.ok().body(annonceService.findAnnonceByModeDeRemise(modeDeRemise));
    }
}
