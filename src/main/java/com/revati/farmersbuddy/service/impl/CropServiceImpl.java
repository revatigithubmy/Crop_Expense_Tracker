package com.revati.farmersbuddy.service.impl;

import com.revati.farmersbuddy.entity.Crop;
import com.revati.farmersbuddy.entity.Farmer;
import com.revati.farmersbuddy.repository.CropRepository;
import com.revati.farmersbuddy.repository.FarmerRepository;
import com.revati.farmersbuddy.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CropServiceImpl implements CropService {

    private final CropRepository cropRepository;
    private final FarmerRepository farmerRepository;

    @Override
    public void addCrop(Crop crop, Long farmerId) {

        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        crop.setFarmer(farmer);

        cropRepository.save(crop);
    }

    @Override
    public List<Crop> getCropsByFarmer(Long farmerId) {
        return cropRepository.findByFarmerId(farmerId);
    }

}
