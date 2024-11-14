package com.uca.m2.pdd.Mapper;

import com.uca.m2.pdd.Model.dto.UserDto;
import com.uca.m2.pdd.Model.entity.Users;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {
    public static UserDto toUserDto(Users entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setUsername(entity.getUsername());
        userDto.setPassword(entity.getPassword());
        userDto.setNom(entity.getNom());
        userDto.setPrenom(entity.getPrenom());
        userDto.setEmail(entity.getEmail());
        userDto.setNumeroTelephone(entity.getNumeroTelephone());
        userDto.setLatitude(entity.getLatitude());
        userDto.setLongitude(entity.getLongitude());
        return userDto;
    }

    public static Users toUserEntity(UserDto userDto) {
        Users entity = new Users();
        entity.setId(userDto.getId());
        entity.setUsername(userDto.getUsername());
        entity.setNom(userDto.getNom());
        entity.setPrenom(userDto.getPrenom());
        entity.setPassword(userDto.getPassword());
        entity.setEmail(userDto.getEmail());
        entity.setNumeroTelephone(userDto.getNumeroTelephone());
        entity.setLongitude(userDto.getLongitude());
        entity.setLatitude(userDto.getLatitude());
        return entity;
    }

    public void updateUserFromDto(UserDto userDto, Users user) {
        if (userDto == null) {
            return;
        }
        user.setNom(userDto.getNom());
        user.setPrenom(userDto.getPrenom());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setNumeroTelephone(userDto.getNumeroTelephone());
        user.setLongitude(userDto.getLongitude());
        user.setLatitude(userDto.getLatitude());

    }
}
