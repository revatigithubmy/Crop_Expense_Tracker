package com.revati.farmersbuddy.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HarvestRequestDTO {

    private BigDecimal quantitySold;
    private BigDecimal pricePerUnit;
}
