package com.revati.farmersbuddy.service;

import com.revati.farmersbuddy.dto.response.DashboardResponseDTO;



public interface DashboardService {
    DashboardResponseDTO getCropSummary(Long cropId);
    DashboardResponseDTO getFarmerSummary(Long farmerId);
}
