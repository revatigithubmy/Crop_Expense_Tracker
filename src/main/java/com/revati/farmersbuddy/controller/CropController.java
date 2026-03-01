package com.revati.farmersbuddy.controller;

import com.revati.farmersbuddy.dto.request.CropRequestDTO;
import com.revati.farmersbuddy.dto.response.CropResponseDTO;
import com.revati.farmersbuddy.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crops")
@RequiredArgsConstructor
public class CropController {

    private final CropService cropService;

    @PostMapping("/farmer/{farmerId}")
    public ResponseEntity<CropResponseDTO> addCrop(
            @PathVariable Long farmerId,
            @RequestBody CropRequestDTO request) {

        return ResponseEntity.ok(cropService.addCrop(farmerId, request));
    }

    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<CropResponseDTO>> getCropsByFarmer(
            @PathVariable Long farmerId) {

        return ResponseEntity.ok(cropService.getCropsByFarmerId(farmerId));
    }

    @GetMapping("/{cropId}")
    public ResponseEntity<CropResponseDTO> getCropById(
            @PathVariable Long cropId) {

        return ResponseEntity.ok(cropService.getCropById(cropId));
    }

    @PutMapping("/{cropId}")
    public ResponseEntity<CropResponseDTO> updateCrop(
            @PathVariable Long cropId,
            @RequestBody CropRequestDTO request) {

        return ResponseEntity.ok(cropService.updateCrop(cropId, request));
    }

    @DeleteMapping("/{cropId}")
    public ResponseEntity<String> deleteCrop(
            @PathVariable Long cropId) {

        cropService.deleteCrop(cropId);
        return ResponseEntity.ok("Crop deleted successfully");
    }

}
