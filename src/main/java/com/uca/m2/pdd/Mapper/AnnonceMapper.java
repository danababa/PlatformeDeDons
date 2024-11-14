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
        dto.setDatePublication(entity.getDatePublication());
        dto.setLongitude(entity.getLongitude());
        dto.setLatitude(entity.getLatitude());
        dto.setModeDeRemise(entity.getModeDeRemiseEnum());
        dto.setMotsCles(entity.getMotsCles());
        return dto;
    }

    public static Annonce toAnnonceEntity(AnnonceDto dto) {
        Annonce entity = new Annonce();
        entity.setId(dto.getId());
        entity.setTitre(dto.getTitre());
        entity.setDescription(dto.getDescription());
        entity.setEtat(dto.getEtat());
        entity.setDatePublication(dto.getDatePublication());
        entity.setLongitude(dto.getLongitude());
        entity.setLatitude(dto.getLatitude());
        entity.setModeDeRemiseEnum(dto.getModeDeRemise());
        entity.setMotsCles(dto.getMotsCles());
        return entity;
    }

    public static void updateAnnonceEntityFromDto(AnnonceDto dto, Annonce entity) {
        if (dto.getTitre() != null) entity.setTitre(dto.getTitre());
        if (dto.getDescription() != null) entity.setDescription(dto.getDescription());
        if (dto.getEtat() != null) entity.setEtat(dto.getEtat());
        if (dto.getDatePublication() != null) entity.setDatePublication(dto.getDatePublication());
        if (dto.getLongitude() != 0) entity.setLongitude(dto.getLongitude());
        if (dto.getLatitude() != 0) entity.setLatitude(dto.getLatitude());
        if (dto.getModeDeRemise() != null) entity.setModeDeRemiseEnum(dto.getModeDeRemise());
        if (dto.getMotsCles() != null && !dto.getMotsCles().isEmpty()) entity.setMotsCles(dto.getMotsCles());
    }
}
