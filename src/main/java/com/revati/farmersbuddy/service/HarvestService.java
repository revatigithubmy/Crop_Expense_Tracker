package com.revati.farmersbuddy.service;

import com.revati.farmersbuddy.dto.request.HarvestRequestDTO;
import com.revati.farmersbuddy.dto.response.HarvestResponseDTO;

public interface HarvestService {

    HarvestResponseDTO addHarvest(Long cropId, HarvestRequestDTO requestDTO);

    HarvestResponseDTO getHarvestByCrop(Long cropId);

    HarvestResponseDTO updateHarvest(Long id, HarvestRequestDTO requestDTO);

    void deleteHarvest(Long id);

}
