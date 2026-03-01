package com.revati.farmersbuddy.service;

import com.revati.farmersbuddy.dto.request.FarmerRequestDTO;
import com.revati.farmersbuddy.dto.response.FarmerResponseDTO;
import com.revati.farmersbuddy.entity.Farmer;

import java.util.List;

public interface FarmerService {

    List<FarmerResponseDTO> getAllFarmers();

    FarmerResponseDTO getFarmerById(Long id);

    FarmerResponseDTO updateFarmer(Long id, FarmerRequestDTO farmerRequestDTO);

    void deleteFarmer(Long id);

}
