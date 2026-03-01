package com.revati.farmersbuddy.dto.response;

import com.revati.farmersbuddy.enums.Season;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CropResponseDTO {

    private Long id;
    private String cropName;
    private Season season;
    private Double landArea;
    private LocalDate sowingDate;

}
