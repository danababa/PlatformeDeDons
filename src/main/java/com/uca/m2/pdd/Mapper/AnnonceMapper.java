package com.uca.m2.pdd.Mapper;

import com.uca.m2.pdd.Model.dto.AnnonceDto;
import com.uca.m2.pdd.Model.entity.Annonce;

public class AnnonceMapper {
    public static AnnonceDto toAnnonceDto(Annonce entity) {
        AnnonceDto dto = new AnnonceDto();
        dto.setId(entity.getId());
        dto.setTitre(entity.getTitre());
        dto.setDescription(entity.getDescription());
        dto.setEtat(entity.getEtat());
        return dto;
    }

    public static Annonce toAnnonceEntity(AnnonceDto dto) {
        Annonce entity = new Annonce();
        entity.setId(dto.getId());
        entity.setTitre(dto.getTitre());
        entity.setDescription(dto.getDescription());
        entity.setEtat(dto.getEtat());
        return entity;
    }
}
