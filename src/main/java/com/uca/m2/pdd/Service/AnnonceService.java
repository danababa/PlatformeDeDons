package com.uca.m2.pdd.Service;

import com.uca.m2.pdd.Exception.NotFoundException;
import com.uca.m2.pdd.Mapper.AnnonceMapper;
import com.uca.m2.pdd.Model.Enum.ModeDeRemiseEnum;
import com.uca.m2.pdd.Model.dto.AnnonceDto;
import com.uca.m2.pdd.Model.entity.Annonce;
import com.uca.m2.pdd.Repository.AnnonceRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public AnnonceDto createAnnonce(AnnonceDto annonceDto){
        //TODO check if annonce already exists
//        if (annonceRepository.findByTitre(annonceDto.getTitre()).isPresent()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Annonce with this titre already exists: " + annonce.getTitre());
//        }
        Annonce annonce = AnnonceMapper.toAnnonceEntity(annonceDto);
        Annonce savedAnnonce = annonceRepository.save(annonce);
        return AnnonceMapper.toAnnonceDto(savedAnnonce);
    }

    /**
     * Get all annonces
     * @return list of annonces
     */
    public List<AnnonceDto> getAllAnnonces() {
        return annonceRepository.findAll().stream().map(AnnonceMapper::toAnnonceDto).collect(Collectors.toList());
    }

    /**
     * Get annonce by id if it exists
     * @param id of annonce
     * @return annonce
     */
    public AnnonceDto getAnnonceById(UUID id){
        Annonce annonce = annonceRepository.findById(id).orElseThrow(()-> new NotFoundException("Annonce not found"));
        return AnnonceMapper.toAnnonceDto(annonce);
    }

    //TODO update
    public AnnonceDto updateAnnonce(AnnonceDto annonceDto, UUID id) {
        Annonce existingAnnonce = annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found"));
        AnnonceMapper.updateAnnonceEntityFromDto(annonceDto, existingAnnonce);
        Annonce savedAnnonce = annonceRepository.save(existingAnnonce);

        return AnnonceMapper.toAnnonceDto(savedAnnonce);
    }

   public void deleteAnnonce(UUID id){
        Annonce annonce = annonceRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Annonce not found"));
        annonceRepository.deleteById(annonce.getId());
    }

    /**
     * Get list of all annonces with a certain mode de remise
     * @param modeDeRemise
     * @return list of annonces Dto
     */
    public List<AnnonceDto> findAnnonceByModeDeRemise(ModeDeRemiseEnum modeDeRemise){
            List<Annonce> annoncesList = annonceRepository.findByModeDeRemise(modeDeRemise);

        return annoncesList.stream().map(AnnonceMapper::toAnnonceDto).collect(Collectors.toList());
    }



}
