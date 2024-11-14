package com.uca.m2.pdd.Service;

import com.uca.m2.pdd.Mapper.RechercheMapper;
import com.uca.m2.pdd.Model.dto.RechercheDto;
import com.uca.m2.pdd.Model.entity.Recherche;
import com.uca.m2.pdd.Model.dto.RechercheDto;
import com.uca.m2.pdd.Repository.RechercheRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class RechercheService {

    @Autowired
    private RechercheRepository rechercheRepository;

    /**
     * Get a recherche by id
     * @param id
     * @throws ResponseStatusException
     * @return Recherche
     */
    @Transactional
    public RechercheDto getRechercheById(UUID id) {
        if (!rechercheRepository.existsById(id)) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recherche not found");
        } else {
            Recherche recherche = rechercheRepository.getReferenceById(id);
            return RechercheMapper.toRechercheDto(recherche);
        }
    }

    public Recherche saveRecherche(Recherche recherche) {
        return rechercheRepository.save(recherche);
    }

    public void deleteRecherche(UUID id) {
        if (!rechercheRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recherche not found");
        } else {
            rechercheRepository.deleteById(id);
        }
    }
}
