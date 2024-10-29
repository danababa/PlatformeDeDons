package com.uca.m2.pdd.Model.dto;

import com.uca.m2.pdd.util.FieldUtils;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FavoritesDto {
    private UUID userId;
    private UUID annonceId;

    @NotNull
    @Pattern(regexp = FieldUtils.REGEX_DATE_VALIDATION, message = FieldUtils.ERROR_MESSAGE_DATE_VALIDATION)
    private String dateAjouter;
}
