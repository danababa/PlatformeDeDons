package com.uca.m2.pdd.Mapper;

import com.uca.m2.pdd.Model.dto.LotDto;
import com.uca.m2.pdd.Model.entity.Lot;

/**
 * Mapper pour convertir entre Lot et LotDto.
 */
public class LotMapper {

    public static LotDto toLotDto(Lot lot) {
        if (lot == null) return null;
        LotDto dto = new LotDto();
        dto.setId(lot.getId());
        dto.setUserId(lot.getUserId());
        dto.setTitre(lot.getTitre());
        dto.setDescription(lot.getDescription());
        dto.setDateCreation(lot.getDateCreation());
        dto.setAnnoncesIds(lot.getAnnoncesIds());
        return dto;
    }

    public static Lot toLotEntity(LotDto dto) {
        if (dto == null) return null;
        Lot lot = new Lot();
        lot.setId(dto.getId());
        lot.setUserId(dto.getUserId());
        lot.setTitre(dto.getTitre());
        lot.setDescription(dto.getDescription());
        lot.setDateCreation(dto.getDateCreation() != null ? dto.getDateCreation() : lot.getDateCreation());
        lot.setAnnoncesIds(dto.getAnnoncesIds());
        return lot;
    }
}
