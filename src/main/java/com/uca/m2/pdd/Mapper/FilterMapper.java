package com.uca.m2.pdd.Mapper;

import com.uca.m2.pdd.Model.dto.FilterDto;
import com.uca.m2.pdd.Model.entity.Filter;

public class FilterMapper {
    public static FilterDto toFilterDto(Filter filter) {
        if (filter == null) return null;

        FilterDto dto = new FilterDto();
        dto.setId(filter.getId());
        dto.setUserId(filter.getUserId());
        dto.setEtat(filter.getEtat());
        dto.setModeDeRemise(filter.getModeDeRemise());
        dto.setMotsCles(filter.getMotsCles());
        dto.setDateDePublication(filter.getDateDePublication());
        return dto;
    }

    public static Filter toFilterEntity(FilterDto dto) {
        if (dto == null) return null;

        Filter filter = new Filter();
        filter.setId(dto.getId());
        filter.setUserId(dto.getUserId()); // userId sera défini dans le service à partir de l'URL
        filter.setEtat(dto.getEtat());
        filter.setModeDeRemise(dto.getModeDeRemise());
        filter.setMotsCles(dto.getMotsCles());
        filter.setDateDePublication(dto.getDateDePublication());
        return filter;
    }

    public static void updateFilterEntityFromDto(FilterDto dto, Filter filter) {
        if (dto.getEtat() != null) filter.setEtat(dto.getEtat());
        if (dto.getModeDeRemise() != null) filter.setModeDeRemise(dto.getModeDeRemise());
        if (dto.getMotsCles() != null) filter.setMotsCles(dto.getMotsCles());
        if (dto.getDateDePublication() != null) filter.setDateDePublication(dto.getDateDePublication());
    }
}
