package com.uca.m2.pdd.Model.dto;

import com.uca.m2.pdd.Model.Enum.ModeDeRemiseEnum;
import com.uca.m2.pdd.util.FieldUtils;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AnnonceDto {
    private UUID id;

    @NotNull
    private String titre;

    @NotNull
    private String description;

    @NotNull
    private String etat;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datePublication;

    @NotNull
    @DecimalMin(value = "-180", inclusive = true)
    @DecimalMax(value = "180", inclusive = true )
    private double longitude;

    @NotNull
    @DecimalMin(value = "-90", inclusive = true)
    @DecimalMax(value = "90", inclusive = true )
    private double latitude;

    @NotNull
    private ModeDeRemiseEnum modeDeRemise;

    @NotEmpty
    @Size(min=FieldUtils.DEFAULT_LIST_MIN_SIZE, max=FieldUtils.DEFAULT_LIST_MAX_SIZE)
    private List<String> motsCles;

    
}
