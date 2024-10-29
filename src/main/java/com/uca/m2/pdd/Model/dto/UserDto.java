package com.uca.m2.pdd.Model.dto;

import com.uca.m2.pdd.util.FieldUtils;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {
    @NotNull
    @Size(min= FieldUtils.DEFAULT_STRING_MIN_SIZE, max=FieldUtils.DEFAULT_STRING_MAX_SIZE)
    private String username;

    @NotNull
    @Size(min= FieldUtils.DEFAULT_STRING_MIN_SIZE, max=FieldUtils.DEFAULT_STRING_MAX_SIZE)
    //TODO: Add password hashing
    @Pattern(regexp = FieldUtils.REGEx_PASSWORD_VALIDATION, message = FieldUtils.ERROR_MESSAGE_PASSWORD_VALIDATION)
    private String password;

    @NotNull
    @Size(min= FieldUtils.DEFAULT_STRING_MIN_SIZE, max=FieldUtils.DEFAULT_STRING_MAX_SIZE)
    private String nom;

    @NotNull
    @Size(min= FieldUtils.DEFAULT_STRING_MIN_SIZE, max=FieldUtils.DEFAULT_STRING_MAX_SIZE)
    private String prenom;

    @NotNull
    @Email
    @Size(min= FieldUtils.DEFAULT_STRING_MIN_SIZE, max=FieldUtils.DEFAULT_STRING_MAX_SIZE)
    private String email;

    @Pattern(regexp = FieldUtils.REGEX_PHONE_NUMBER_VALIDATION, message = FieldUtils.ERROR_MESSAGE_PHONE_NUMBER_VALIDATION)
    private String numeroTelephone;

    @Size(min= FieldUtils.DEFAULT_LONGITUDE_MIN_SIZE, max=FieldUtils.DEFAULT_LONGITUDE_MAX_SIZE)
    private double longitude;

    @Size(min= FieldUtils.DEFAULT_LATITUDE_MIN_SIZE, max=FieldUtils.DEFAULT_LATITUDE_MAX_SIZE)
    private double latitude;

}

