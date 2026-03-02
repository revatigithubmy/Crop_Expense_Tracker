package com.revati.farmersbuddy.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfitResponseDTO {

    private String cropName;
    private BigDecimal totalRevenue;
    private BigDecimal totalExpense;
    private BigDecimal netProfit;

}
