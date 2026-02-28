package com.revati.farmersbuddy.controller;

import com.revati.farmersbuddy.entity.Crop;
import com.revati.farmersbuddy.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crop")
@RequiredArgsConstructor
public class CropController {

    private final CropService cropService;

    @PostMapping("/add/{farmerId}")
    public String addCrop(@RequestBody Crop crop,
                          @PathVariable Long farmerId) {

        cropService.addCrop(crop, farmerId);
        return "Crop added successfully";
    }

    @GetMapping("/farmer/{farmerId}")
    public List<Crop> getFarmerCrops(@PathVariable Long farmerId) {
        return cropService.getCropsByFarmer(farmerId);
    }

}
