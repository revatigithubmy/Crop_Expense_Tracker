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

    @GetMapping("/crop/{cropId}")
    public ResponseEntity<DashboardResponseDTO> getCropSummary(
            @PathVariable Long cropId) {

        return ResponseEntity.ok(
                dashboardService.getCropSummary(cropId)
        );
    }

    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<DashboardResponseDTO> getFarmerSummary(
            @PathVariable Long farmerId) {

        return ResponseEntity.ok(
                dashboardService.getFarmerSummary(farmerId)
        );
    }
}
