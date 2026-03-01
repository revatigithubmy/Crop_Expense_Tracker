package com.revati.farmersbuddy.service.impl;


import com.revati.farmersbuddy.dto.request.HarvestRequestDTO;
import com.revati.farmersbuddy.dto.response.HarvestResponseDTO;
import com.revati.farmersbuddy.entity.Crop;
import com.revati.farmersbuddy.entity.Harvest;
import com.revati.farmersbuddy.exception.ResourceNotFoundException;
import com.revati.farmersbuddy.repository.CropRepository;
import com.revati.farmersbuddy.repository.HarvestRepository;
import com.revati.farmersbuddy.service.HarvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final CropRepository cropRepository;

    @Override
    public HarvestResponseDTO addHarvest(Long cropId, HarvestRequestDTO requestDTO) {

        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found"));

        harvestRepository.findByCropId(cropId)
                .ifPresent(h -> {
                    throw new RuntimeException("Harvest already exists for this crop");
                });

        Harvest harvest = Harvest.builder()
                .quantitySold(requestDTO.getQuantitySold())
                .pricePerUnit(requestDTO.getPricePerUnit())
                .crop(crop)
                .build();

        Harvest saved = harvestRepository.save(harvest);

        return mapToResponse(saved);
    }

    @Override
    public HarvestResponseDTO getHarvestByCrop(Long cropId) {

        Harvest harvest = harvestRepository.findByCropId(cropId)
                .orElseThrow(() -> new ResourceNotFoundException("Harvest not found"));

        return mapToResponse(harvest);
    }

    @Override
    public HarvestResponseDTO updateHarvest(Long id, HarvestRequestDTO requestDTO) {

        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Harvest not found"));

        harvest.setQuantitySold(requestDTO.getQuantitySold());
        harvest.setPricePerUnit(requestDTO.getPricePerUnit());

        Harvest updated = harvestRepository.save(harvest);

        return mapToResponse(updated);
    }


    @Override
    public void deleteHarvest(Long id) {

        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Harvest not found"));

        harvestRepository.delete(harvest);
    }

    private HarvestResponseDTO mapToResponse(Harvest harvest) {

        BigDecimal totalRevenue =
                harvest.getQuantitySold().multiply(harvest.getPricePerUnit());

        return HarvestResponseDTO.builder()
                .id(harvest.getId())
                .quantitySold(harvest.getQuantitySold())
                .pricePerUnit(harvest.getPricePerUnit())
                .totalRevenue(totalRevenue)
                .cropId(harvest.getCrop().getId())
                .build();
    }

}
