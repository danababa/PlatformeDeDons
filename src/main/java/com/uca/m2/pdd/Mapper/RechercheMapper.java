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
        // Map user entity to UserDto if required
        if (rechercheEntity.getUser() != null) {
            UserDto userDto = new UserDto();
            userDto.setId(rechercheDto.getUser().getId());
            // Set other fields of UserDto as needed
            rechercheDto.setUser(userDto);
        }

        return rechercheDto;
    }

    public Recherche toRechercheEntity(RechercheDto rechercheDto) {
        if (rechercheDto == null) {
            return null;
        }

        Recherche rechercheEntity = new Recherche();
        rechercheEntity.setId(rechercheDto.getId());
        rechercheEntity.setMotsCles(rechercheDto.getMotsCles());
        // Map UserDto back to User entity if required
        if (rechercheDto.getUser() != null) {
            Users user = new Users();
            user.setId(rechercheDto.getUser().getId());
            // Set other fields of Users as needed
            rechercheEntity.setUser(user);
        }

        return rechercheEntity;
    }
}
