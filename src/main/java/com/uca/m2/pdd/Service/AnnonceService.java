package com.uca.m2.pdd.Service;

import com.uca.m2.pdd.Mapper.AnnonceMapper;
import com.uca.m2.pdd.Model.dto.AnnonceDto;
import com.uca.m2.pdd.Model.entity.Annonce;
import com.uca.m2.pdd.Repository.AnnonceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AnnonceService {
    private final AnnonceRepository annonceRepository;

    private AnnonceService(AnnonceRepository annonceRepository){
        this.annonceRepository = annonceRepository;
    }

    /**
     * Create a new annonce
     * @param annonceDto body to create
     * @return created annonce body
     */
    private AnnonceDto createAnnonce(AnnonceDto annonceDto){
        Annonce annonce = AnnonceMapper.toAnnonceEntity(annonceDto);
        if(annonceRepository.existsById(annonce.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Annonce with this id already exists" + annonce.getId().toString());
        }
        Annonce savedAnnonce = annonceRepository.save(annonce);
        return AnnonceMapper.toAnnonceDto(savedAnnonce);
    }

    /**
     * Get annonce by id if it exists
     * @param id of annonce
     * @return annonce
     */
    public AnnonceDto getAnnonceById(UUID id){
        Annonce annonce = annonceRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Annonce with this id not found" + id.toString()));
        return AnnonceMapper.toAnnonceDto(annonce);
    }

//    public AnnonceDto updateAnnonce(AnnonceDto annonceDto, UUID id){
//        Annonce annonce = AnnonceMapper.toAnnonceEntity(annonceDto);
//        if(annonceRepository.existsById(id)){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Annonce with this id not found" + id.toString()));
//        }
//        Annonce savedAnnonce = annonceRepository.save(annonce);
//
//    }
}
