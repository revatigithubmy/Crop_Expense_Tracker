package com.revati.farmersbuddy.service.impl;

import com.revati.farmersbuddy.dto.request.CropRequestDTO;
import com.revati.farmersbuddy.dto.response.CropResponseDTO;
import com.revati.farmersbuddy.entity.Crop;
import com.revati.farmersbuddy.entity.Farmer;
import com.revati.farmersbuddy.exception.ResourceNotFoundException;
import com.revati.farmersbuddy.repository.CropRepository;
import com.revati.farmersbuddy.repository.FarmerRepository;
import com.revati.farmersbuddy.service.CropService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CropServiceImpl implements CropService {

    private final CropRepository cropRepository;
    private final FarmerRepository farmerRepository;
    private final ModelMapper modelMapper;

    @Override
    public CropResponseDTO addCrop(Long farmerId, CropRequestDTO request) {

        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Farmer not found"));

        Crop crop = modelMapper.map(request, Crop.class);
        crop.setFarmer(farmer);

        Crop savedCrop = cropRepository.save(crop);

        return modelMapper.map(savedCrop, CropResponseDTO.class);
    }

    @Override
    public List<CropResponseDTO> getCropsByFarmerId(Long farmerId) {

        List<Crop> crops = cropRepository.findByFarmerId(farmerId);

        return crops.stream()
                .map(crop -> modelMapper.map(crop, CropResponseDTO.class))
                .toList();
    }

    @Override
    public CropResponseDTO getCropById(Long cropId) {

        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found"));

        return modelMapper.map(crop, CropResponseDTO.class);
    }

    @Override
    public CropResponseDTO updateCrop(Long cropId, CropRequestDTO request) {

        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found"));

        crop.setCropName(request.getCropName());
        crop.setSeason(request.getSeason());
        crop.setLandArea(request.getLandArea());
        crop.setSowingDate(request.getSowingDate());

        Crop updated = cropRepository.save(crop);

        return modelMapper.map(updated, CropResponseDTO.class);
    }

    @Override
    public void deleteCrop(Long cropId) {

        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found"));

        cropRepository.delete(crop);
    }

}
