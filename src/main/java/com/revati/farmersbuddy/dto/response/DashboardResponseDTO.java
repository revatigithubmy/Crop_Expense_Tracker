package com.revati.farmersbuddy.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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
    // This will hold the list of individual crop profits for the farmer
    private List<ProfitResponseDTO> cropBreakdown;

}
