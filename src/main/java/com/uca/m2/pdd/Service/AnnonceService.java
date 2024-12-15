package com.uca.m2.pdd.Service;

import com.uca.m2.pdd.Exception.NotFoundException;
import com.uca.m2.pdd.Mapper.AnnonceMapper;
import com.uca.m2.pdd.Model.Enum.ModeDeRemiseEnum;
import com.uca.m2.pdd.Model.dto.AnnonceDto;
import com.uca.m2.pdd.Model.entity.Annonce;
import com.uca.m2.pdd.Model.entity.Filter;
import com.uca.m2.pdd.Repository.AnnonceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service pour gérer la logique métier liée aux annonces.
 */
@Service
public class AnnonceService {
    private final AnnonceRepository annonceRepository;

    public AnnonceService(AnnonceRepository annonceRepository){
        this.annonceRepository = annonceRepository;
    }

    /**
     * Crée une nouvelle annonce.
     * @param annonceDto Données de l'annonce à créer
     * @return L'annonce créée sous forme de DTO
     */
    public void createAnnonce(AnnonceDto annonceDto) {
        Annonce annonce = new Annonce();
        annonce.setTitre(annonceDto.getTitre());
        annonce.setDescription(annonceDto.getDescription());
        annonce.setEtat(annonceDto.getEtat());
        annonce.setDatePublication(annonceDto.getDatePublication());
        annonce.setLongitude(annonceDto.getLongitude());
        annonce.setLatitude(annonceDto.getLatitude());
        annonce.setModeDeRemiseEnum(annonceDto.getModeDeRemise());
        annonce.setMotsCles(annonceDto.getMotsCles());

        annonceRepository.save(annonce);
    }

    /**
     * Récupère toutes les annonces.
     * @return Liste de DTO représentant toutes les annonces
     */
    public List<AnnonceDto> getAllAnnonces() {
            return annonceRepository.findAll()
                    .stream()
                    .map(AnnonceMapper::toAnnonceDto) // Assuming a mapper exists
                    .toList();
    }


    /**
     * Récupère une annonce par son ID.
     * @param id ID de l'annonce
     * @return AnnonceDto correspondant
     * @throws NotFoundException si l'annonce n'est pas trouvée
     */
    public AnnonceDto getAnnonceById(UUID id){
        Annonce annonce = annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found"));
        return AnnonceMapper.toAnnonceDto(annonce);
    }

    /**
     * Met à jour une annonce existante.
     * @param annonceDto DTO contenant les nouvelles données
     * @param id ID de l'annonce à mettre à jour
     * @return L'annonce mise à jour sous forme de DTO
     * @throws NotFoundException si l'annonce n'existe pas
     */
    public AnnonceDto updateAnnonce(AnnonceDto annonceDto, UUID id) {
        Annonce existingAnnonce = annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found"));
        AnnonceMapper.updateAnnonceEntityFromDto(annonceDto, existingAnnonce);
        Annonce savedAnnonce = annonceRepository.save(existingAnnonce);

        return AnnonceMapper.toAnnonceDto(savedAnnonce);
    }

    /**
     * Supprime une annonce par son ID.
     * @param id ID de l'annonce à supprimer
     * @throws IllegalArgumentException si l'annonce n'est pas trouvée
     */
    public void deleteAnnonce(UUID id){
        Annonce annonce = annonceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Annonce not found"));
        annonceRepository.deleteById(annonce.getId());
    }

    /**
     * Récupère les annonces ayant un certain mode de remise.
     * @param modeDeRemise Mode de remise (EN_MAIN_PROPRE ou ENVOI)
     * @return Liste de DTO correspondant aux annonces filtrées par mode de remise
     */
    public List<AnnonceDto> findAnnonceByModeDeRemise(ModeDeRemiseEnum modeDeRemise){
        List<Annonce> annoncesList = annonceRepository.findByModeDeRemiseEnum(modeDeRemise);
        return annoncesList.stream().map(AnnonceMapper::toAnnonceDto).collect(Collectors.toList());
    }

    /**
     * Recherche des annonces en fonction de critères optionnels.
     * @param etat État de l'objet (optionnel)
     * @param modeDeRemise Mode de remise sous forme de chaîne (optionnel)
     * @param motsCles Liste de mots-clés (optionnel)
     * @param dateDePublication Date de publication (optionnel)
     * @return Liste d'AnnonceDto correspondant aux annonces trouvées
     */
    public List<AnnonceDto> searchAnnonces(String etat, String modeDeRemise, List<String> motsCles, LocalDate dateDePublication) {
        ModeDeRemiseEnum modeDeRemiseEnum = null;
        if (modeDeRemise != null) {
            try {
                modeDeRemiseEnum = ModeDeRemiseEnum.valueOf(modeDeRemise);
            } catch (IllegalArgumentException e) {
                // Si le modeDeRemise ne correspond pas à une valeur de l'enum, on peut ignorer ou lever une exception.
            }
        }

        List<Annonce> annonces = annonceRepository.searchAnnonces(
                etat, modeDeRemiseEnum, motsCles, dateDePublication
        );

        return annonces.stream()
                .map(AnnonceMapper::toAnnonceDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupère les annonces correspondant à un filtre enregistré.
     * @param filter Objet Filter contenant les critères
     * @return Liste d'entités Annonce correspondant au filtre
     */
    public List<Annonce> getAnnoncesByFilter(Filter filter) {
        ModeDeRemiseEnum modeDeRemiseEnum = null;
        if (filter.getModeDeRemise() != null) {
            try {
                modeDeRemiseEnum = ModeDeRemiseEnum.valueOf(filter.getModeDeRemise());
            } catch (IllegalArgumentException e) {
                // Gérer l'erreur si la valeur n'est pas dans l'enum
            }
        }

        return annonceRepository.searchAnnonces(
                filter.getEtat(),
                modeDeRemiseEnum,
                filter.getMotsCles(),
                filter.getDateDePublication()
        );
    }
}
