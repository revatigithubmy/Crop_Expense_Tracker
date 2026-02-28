package com.revati.farmersbuddy.service;

import com.revati.farmersbuddy.entity.Crop;

import java.util.List;

public interface CropService {

    void addCrop(Crop crop, Long farmerId);
    List<Crop> getCropsByFarmer(Long farmerId);

}
