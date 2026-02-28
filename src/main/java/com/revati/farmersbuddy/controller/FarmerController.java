package com.revati.farmersbuddy.controller;

import com.revati.farmersbuddy.entity.Farmer;
import com.revati.farmersbuddy.service.FarmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/farmers")
@RequiredArgsConstructor
public class FarmerController {

    @Autowired
    private FarmerService farmerService;


    // Get All Farmers
    @GetMapping
    public List<Farmer> getAllFarmers() {
        return farmerService.getAllFarmers();
    }

    // Get Farmer By ID
    @GetMapping("/{id}")
    public Farmer getFarmerById(@PathVariable Long id) {
        return farmerService.getFarmerById(id);
    }

    // Update Farmer
    @PutMapping("/{id}")
    public Farmer updateFarmer(@PathVariable Long id,
                               @RequestBody Farmer farmer) {
        return farmerService.updateFarmer(id, farmer);
    }

    // Delete Farmer
    @DeleteMapping("/{id}")
    public String deleteFarmer(@PathVariable Long id) {
        farmerService.deleteFarmer(id);
        return "Farmer deleted successfully";
    }


}
