package com.uca.m2.pdd.Model.dto;

import com.uca.m2.pdd.util.FieldUtils;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {
    private UUID id;

    @Size(min= FieldUtils.DEFAULT_STRING_MIN_SIZE, max=FieldUtils.DEFAULT_STRING_MAX_SIZE)
    private String username;

    @Size(min= FieldUtils.DEFAULT_STRING_MIN_SIZE, max=FieldUtils.DEFAULT_STRING_MAX_SIZE)
    //TODO: Add password hashing and fix pattern
    //@Pattern(regexp = FieldUtils.REGEX_PASSWORD_VALIDATION, message = FieldUtils.ERROR_MESSAGE_PASSWORD_VALIDATION)
    private String password;

    @Size(min= FieldUtils.DEFAULT_STRING_MIN_SIZE, max=FieldUtils.DEFAULT_STRING_MAX_SIZE)
    private String nom;

    @Size(min= FieldUtils.DEFAULT_STRING_MIN_SIZE, max=FieldUtils.DEFAULT_STRING_MAX_SIZE)
    private String prenom;

    @Email
    @Size(min= FieldUtils.DEFAULT_STRING_MIN_SIZE, max=FieldUtils.DEFAULT_STRING_MAX_SIZE)
    private String email;

    @Pattern(regexp = FieldUtils.REGEX_PHONE_NUMBER_VALIDATION, message = FieldUtils.ERROR_MESSAGE_PHONE_NUMBER_VALIDATION)
    private String numeroTelephone;

    @DecimalMin(value = "-180", inclusive = true)
    @DecimalMax(value = "180", inclusive = true )
    private double longitude;

    @DecimalMin(value = "-90", inclusive = true)
    @DecimalMax(value = "90", inclusive = true )
    private double latitude;

}

