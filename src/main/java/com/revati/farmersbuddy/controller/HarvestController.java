package com.revati.farmersbuddy.controller;

import com.revati.farmersbuddy.dto.request.HarvestRequestDTO;
import com.revati.farmersbuddy.dto.response.HarvestResponseDTO;
import com.revati.farmersbuddy.service.HarvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/harvest")
@RequiredArgsConstructor
public class HarvestController {
    private final HarvestService harvestService;

    @PostMapping("/add/{cropId}")
    public ResponseEntity<HarvestResponseDTO> addHarvest(
            @PathVariable Long cropId,
            @RequestBody HarvestRequestDTO requestDTO) {

        return ResponseEntity.ok(harvestService.addHarvest(cropId, requestDTO));
    }

    @GetMapping("/crop/{cropId}")
    public ResponseEntity<HarvestResponseDTO> getHarvestByCrop(
            @PathVariable Long cropId) {

        return ResponseEntity.ok(harvestService.getHarvestByCrop(cropId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HarvestResponseDTO> updateHarvest(
            @PathVariable Long id,
            @RequestBody HarvestRequestDTO requestDTO) {

        return ResponseEntity.ok(harvestService.updateHarvest(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHarvest(@PathVariable Long id) {

        harvestService.deleteHarvest(id);
        return ResponseEntity.ok("Harvest deleted successfully");
    }
}
