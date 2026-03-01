package com.revati.farmersbuddy.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponseDTO {

    private Long farmerId;
    private Long cropId;

    private BigDecimal totalExpense;
    private BigDecimal totalRevenue;
    private BigDecimal totalProfit;


}
