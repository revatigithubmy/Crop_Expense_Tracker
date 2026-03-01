package com.revati.farmersbuddy.controller;

import com.revati.farmersbuddy.dto.request.FarmerRequestDTO;
import com.revati.farmersbuddy.dto.response.FarmerResponseDTO;
import com.revati.farmersbuddy.entity.Farmer;
import com.revati.farmersbuddy.service.FarmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/farmers")
@RequiredArgsConstructor
public class FarmerController {

    private final FarmerService farmerService;


    @GetMapping
    public List<FarmerResponseDTO> getAllFarmers() {
        return farmerService.getAllFarmers();
    }

    @GetMapping("/{id}")
    public FarmerResponseDTO getFarmerById(@PathVariable Long id) {
        return farmerService.getFarmerById(id);
    }

    @PutMapping("/{id}")
    public FarmerResponseDTO updateFarmer(
            @PathVariable Long id,
            @RequestBody FarmerRequestDTO farmer) {

        return farmerService.updateFarmer(id, farmer);
    }

    @DeleteMapping("/{id}")
    public String deleteFarmer(@PathVariable Long id) {
        farmerService.deleteFarmer(id);
        return "Farmer deleted successfully";
    }


}
