package com.revati.farmersbuddy.service.impl;

import com.revati.farmersbuddy.entity.Farmer;
import com.revati.farmersbuddy.repository.FarmerRepository;
import com.revati.farmersbuddy.service.FarmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmerServiceImpl implements FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;


    @Override
    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll();
    }

    @Override
    public Farmer getFarmerById(Long id) {
        return farmerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));
    }

    @Override
    public Farmer updateFarmer(Long id, Farmer updatedFarmer) {

        Farmer farmer = getFarmerById(id);

        farmer.setName(updatedFarmer.getName());
        farmer.setVillage(updatedFarmer.getVillage());

        return farmerRepository.save(farmer);
    }

    @Override
    public void deleteFarmer(Long id) {
        Farmer farmer = getFarmerById(id);
        farmerRepository.delete(farmer);
    }

}
