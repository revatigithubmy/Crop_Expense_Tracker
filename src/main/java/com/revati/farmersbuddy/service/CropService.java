package com.revati.farmersbuddy.service;

import com.revati.farmersbuddy.dto.request.CropRequestDTO;
import com.revati.farmersbuddy.dto.response.CropResponseDTO;
import com.revati.farmersbuddy.entity.Crop;

import java.util.List;

public interface CropService {

    CropResponseDTO addCrop(Long farmerId, CropRequestDTO request);

    List<CropResponseDTO> getCropsByFarmerId(Long farmerId);

    CropResponseDTO getCropById(Long cropId);

    CropResponseDTO updateCrop(Long cropId, CropRequestDTO request);

    void deleteCrop(Long cropId);

}
