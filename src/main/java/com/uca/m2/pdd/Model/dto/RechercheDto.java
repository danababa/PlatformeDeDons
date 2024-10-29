package com.uca.m2.pdd.Model.dto;

import com.uca.m2.pdd.util.FieldUtils;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class RechercheDto {
    private UUID id;

    @NotEmpty
    @Size(min = FieldUtils.DEFAULT_LIST_MIN_SIZE, max = FieldUtils.DEFAULT_LIST_MAX_SIZE)
    private List<String> motsCles;

    @NotNull
    private UserDto user;
}
