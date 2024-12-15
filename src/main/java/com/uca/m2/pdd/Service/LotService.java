package com.uca.m2.pdd.Service;

import com.uca.m2.pdd.Mapper.LotMapper;
import com.uca.m2.pdd.Model.dto.LotDto;
import com.uca.m2.pdd.Model.entity.Lot;
import com.uca.m2.pdd.Repository.LotRepository;
import com.uca.m2.pdd.Repository.UsersRepository;
import com.uca.m2.pdd.Repository.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service pour gérer la logique métier liée aux lots.
 */
@Service
public class LotService {

    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AnnonceRepository annonceRepository;

    /**
     * Crée un nouveau lot pour un utilisateur après vérification de l'existence de l'utilisateur.
     * On ne vérifie plus la propriété des annonces, juste que l'utilisateur existe.
     * @param userId ID de l'utilisateur
     * @param lotDto Données du lot
     * @return Le lot créé sous forme de LotDto
     * @throws IllegalArgumentException si l'utilisateur n'existe pas
     */
    public LotDto createLot(UUID userId, LotDto lotDto) {
        if (!usersRepository.existsById(userId)) {
            throw new IllegalArgumentException("Utilisateur non trouvé : " + userId);
        }

        Lot lot = LotMapper.toLotEntity(lotDto);
        lot.setUserId(userId);
        Lot savedLot = lotRepository.save(lot);
        return LotMapper.toLotDto(savedLot);
    }

    /**
     * Récupère tous les lots pour un utilisateur donné.
     * @param userId ID de l'utilisateur
     * @return Liste de LotDto
     */
    public List<LotDto> getLotsByUser(UUID userId) {
        List<Lot> lots = lotRepository.findByUserId(userId);
        return lots.stream().map(LotMapper::toLotDto).collect(Collectors.toList());
    }

    /**
     * Supprime un lot appartenant à un utilisateur.
     * @param userId ID de l'utilisateur
     * @param lotId ID du lot
     * @throws IllegalArgumentException si le lot n'existe pas ou n'appartient pas à l'utilisateur
     */
    public void deleteLot(UUID userId, UUID lotId) {
        Lot lot = lotRepository.findById(lotId)
                .orElseThrow(() -> new IllegalArgumentException("Lot non trouvé"));

        if (!lot.getUserId().equals(userId)) {
            throw new IllegalArgumentException("Ce lot n'appartient pas à l'utilisateur");
        }

        lotRepository.delete(lot);
    }
}
