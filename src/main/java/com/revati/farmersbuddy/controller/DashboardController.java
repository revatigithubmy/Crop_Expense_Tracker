package com.revati.farmersbuddy.controller;

import com.revati.farmersbuddy.dto.response.DashboardResponseDTO;
import com.revati.farmersbuddy.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;
    // --- CROP APIs ---
    @GetMapping("/crop/{cropId}/total-expense")
    public ResponseEntity<DashboardResponseDTO> getTotalExpense(@PathVariable Long cropId) {
        return ResponseEntity.ok(dashboardService.getCropSummary(cropId));
    }

    @GetMapping("/crop/{cropId}/profit")
    public ResponseEntity<DashboardResponseDTO> getCropProfit(@PathVariable Long cropId) {
        return ResponseEntity.ok(dashboardService.getCropSummary(cropId));
    }

    // --- FARMER APIs ---
    @GetMapping("/farmer/{farmerId}/total-investment")
    public ResponseEntity<DashboardResponseDTO> getTotalInvestment(@PathVariable Long farmerId) {
        return ResponseEntity.ok(dashboardService.getFarmerSummary(farmerId));
    }

    @GetMapping("/farmer/{farmerId}/total-profit")
    public ResponseEntity<DashboardResponseDTO> getTotalFarmerProfit(@PathVariable Long farmerId) {
        return ResponseEntity.ok(dashboardService.getFarmerSummary(farmerId));
    }
}
