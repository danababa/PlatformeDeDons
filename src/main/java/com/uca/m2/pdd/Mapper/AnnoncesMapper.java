package com.uca.m2.pdd.Mapper;

import com.uca.m2.pdd.Model.dto.AnnoncesDto;
import com.uca.m2.pdd.Model.entity.Annonces;

public class AnnoncesMapper {
    public static AnnoncesDto toAnnoncesDto(Annonces entity) {
        AnnoncesDto dto = new AnnoncesDto();
        dto.setId(entity.getId());
        dto.setTitre(entity.getTitre());
        dto.setDescription(entity.getDescription());
        dto.setEtat(entity.getEtat());
        return dto;
    }

    public static Annonces toAnnoncesEntity(AnnoncesDto dto) {
        Annonces entity = new Annonces();
        entity.setId(dto.getId());
        entity.setTitre(dto.getTitre());
        entity.setDescription(dto.getDescription());
        entity.setEtat(dto.getEtat());
        return entity;
    }
}
