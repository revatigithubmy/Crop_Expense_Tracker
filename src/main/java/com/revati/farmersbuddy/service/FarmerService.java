package com.revati.farmersbuddy.service;

import com.revati.farmersbuddy.entity.Farmer;

import java.util.List;

public interface FarmerService {

    List<Farmer> getAllFarmers();

    Farmer getFarmerById(Long id);

    Farmer updateFarmer(Long id, Farmer farmer);

    void deleteFarmer(Long id);

}
