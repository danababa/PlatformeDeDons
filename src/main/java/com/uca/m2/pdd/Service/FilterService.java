package com.uca.m2.pdd.Service;

import com.uca.m2.pdd.Mapper.FilterMapper;
import com.uca.m2.pdd.Model.dto.FilterDto;
import com.uca.m2.pdd.Model.entity.Filter;
import com.uca.m2.pdd.Repository.FilterRepository;
import com.uca.m2.pdd.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service pour gérer la logique métier liée aux filtres.
 */
@Service
public class FilterService {

    @Autowired
    private FilterRepository filterRepository;

    @Autowired
    private UsersRepository usersRepository; // Ajout du repository utilisateur

    /**
     * Crée un nouveau filtre pour un utilisateur donné.
     * @param userId Identifiant de l'utilisateur
     * @param filterDto Données du filtre à créer
     * @return Le filtre créé sous forme de FilterDto
     * @throws IllegalArgumentException si l'utilisateur n'existe pas
     */
    public FilterDto createFilter(UUID userId, FilterDto filterDto) {
        boolean userExists = usersRepository.existsById(userId);
        if (!userExists) {
            throw new IllegalArgumentException("Utilisateur introuvable avec l'ID : " + userId);
        }
        Filter filter = FilterMapper.toFilterEntity(filterDto);
        filter.setUserId(userId);
        Filter savedFilter = filterRepository.save(filter);
        return FilterMapper.toFilterDto(savedFilter);
    }

    /**
     * Récupère tous les filtres associés à un utilisateur.
     * @param userId Identifiant de l'utilisateur
     * @return Liste de FilterDto correspondant aux filtres de l'utilisateur
     */
    public List<FilterDto> getFiltersByUser(UUID userId) {
        List<Filter> filters = filterRepository.findByUserId(userId);
        return filters.stream()
                .map(FilterMapper::toFilterDto)
                .collect(Collectors.toList());
    }

    /**
     * Supprime un filtre appartenant à un utilisateur.
     * @param userId Identifiant de l'utilisateur
     * @param filterId Identifiant du filtre à supprimer
     * @throws IllegalArgumentException si le filtre n'existe pas ou si l'utilisateur n'en est pas le propriétaire
     */
    public void deleteFilter(UUID userId, UUID filterId) {
        Filter filter = filterRepository.findById(filterId)
                .orElseThrow(() -> new IllegalArgumentException("Filter not found"));

        if (!filter.getUserId().equals(userId)) {
            throw new IllegalArgumentException("User does not own this filter");
        }

        filterRepository.delete(filter);
    }
}
