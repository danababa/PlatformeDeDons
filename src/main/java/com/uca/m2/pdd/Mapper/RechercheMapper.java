package com.uca.m2.pdd.Mapper;

import com.uca.m2.pdd.Model.dto.RechercheDto;
import com.uca.m2.pdd.Model.dto.UserDto;
import com.uca.m2.pdd.Model.entity.Recherche;
import com.uca.m2.pdd.Model.entity.Users;

public class RechercheMapper {
    public static RechercheDto toRechercheDto(Recherche rechercheEntity) {
        if (rechercheEntity == null) {
            return null;
        }

        RechercheDto rechercheDto = new RechercheDto();
        rechercheDto.setId(rechercheEntity.getId());
        rechercheDto.setMotsCles(rechercheEntity.getMotsCles());
        rechercheDto.setUserId(rechercheEntity.getUserId());

        return rechercheDto;
    }

    public Recherche toRechercheEntity(RechercheDto rechercheDto) {
        if (rechercheDto == null) {
            return null;
        }

        Recherche rechercheEntity = new Recherche();
        rechercheEntity.setId(rechercheDto.getId());
        rechercheEntity.setMotsCles(rechercheDto.getMotsCles());
        rechercheEntity.setUserId(rechercheDto.getUserId());

        return rechercheEntity;
    }
}
