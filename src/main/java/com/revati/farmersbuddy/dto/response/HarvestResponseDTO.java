package com.revati.farmersbuddy.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HarvestResponseDTO {

    private Long id;
    private BigDecimal quantitySold;
    private BigDecimal pricePerUnit;
    private BigDecimal totalRevenue;
    private Long cropId;

}
