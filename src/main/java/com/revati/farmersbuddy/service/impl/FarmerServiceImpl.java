package com.revati.farmersbuddy.service.impl;

import com.revati.farmersbuddy.dto.request.FarmerRequestDTO;
import com.revati.farmersbuddy.dto.response.FarmerResponseDTO;
import com.revati.farmersbuddy.entity.Farmer;
import com.revati.farmersbuddy.repository.FarmerRepository;
import com.revati.farmersbuddy.service.FarmerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmerServiceImpl implements FarmerService {

    private final FarmerRepository farmerRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<FarmerResponseDTO> getAllFarmers() {
        return farmerRepository.findAll()
                .stream()
                .map(farmer -> modelMapper.map(farmer, FarmerResponseDTO.class))
                .toList();
    }

    @Override
    public FarmerResponseDTO getFarmerById(Long id) {

        Farmer farmer = farmerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        return modelMapper.map(farmer, FarmerResponseDTO.class);
    }

    @Override
    public FarmerResponseDTO updateFarmer(Long id, FarmerRequestDTO farmerRequestDTO) {

        Farmer farmer = farmerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        farmer.setName(farmerRequestDTO.getName());
        farmer.setVillage(farmerRequestDTO.getVillage());

        Farmer savedFarmer = farmerRepository.save(farmer);

        return modelMapper.map(savedFarmer, FarmerResponseDTO.class);
    }

    @Override
    public void deleteFarmer(Long id) {

        Farmer farmer = farmerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        farmerRepository.delete(farmer);
    }

}
