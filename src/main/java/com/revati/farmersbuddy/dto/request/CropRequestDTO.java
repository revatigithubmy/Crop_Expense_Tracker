package com.revati.farmersbuddy.dto.request;

import com.revati.farmersbuddy.enums.Season;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter

public class CropRequestDTO {
    private String cropName;
    private Season season;
    private Double landArea;
    private LocalDate sowingDate;
}
