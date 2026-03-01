package com.revati.farmersbuddy.service;

import com.revati.farmersbuddy.dto.response.DashboardResponseDTO;

import java.math.BigDecimal;

public interface DashboardService {
    DashboardResponseDTO getCropSummary(Long cropId);

    DashboardResponseDTO getFarmerSummary(Long farmerId);
}
