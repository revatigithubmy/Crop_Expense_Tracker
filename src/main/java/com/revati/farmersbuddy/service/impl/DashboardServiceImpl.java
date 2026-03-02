package com.revati.farmersbuddy.service.impl;


import com.revati.farmersbuddy.dto.response.DashboardResponseDTO;
import com.revati.farmersbuddy.dto.response.ProfitResponseDTO;
import com.revati.farmersbuddy.entity.Crop;
import com.revati.farmersbuddy.entity.Expense;
import com.revati.farmersbuddy.entity.Harvest;
import com.revati.farmersbuddy.exception.ResourceNotFoundException;
import com.revati.farmersbuddy.repository.CropRepository;
import com.revati.farmersbuddy.repository.ExpenseRepository;
import com.revati.farmersbuddy.repository.HarvestRepository;
import com.revati.farmersbuddy.service.DashboardService;
import com.revati.farmersbuddy.util.ProfitCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final CropRepository cropRepository;
    private final ExpenseRepository expenseRepository;
    private final HarvestRepository harvestRepository;
    private final ProfitCalculator profitCalculator;

    @Override
    public DashboardResponseDTO getCropSummary(Long cropId) {
        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found"));

        // Calculation logic...
        BigDecimal totalExpense = expenseRepository.findByCropId(cropId).stream()
                .map(Expense::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        Harvest harvest = harvestRepository.findByCropId(cropId)
                .orElseThrow(() -> new ResourceNotFoundException("Harvest record missing"));

        BigDecimal totalRevenue = harvest.getQuantitySold().multiply(harvest.getPricePerUnit());
        BigDecimal totalProfit = profitCalculator.calculateProfit(totalRevenue, totalExpense);

        return DashboardResponseDTO.builder()
                .farmerId(crop.getFarmer().getId())
                .cropId(cropId) // Ensure this is set here!
                .totalExpense(totalExpense)
                .totalRevenue(totalRevenue)
                .totalProfit(totalProfit)
                .build();
    }

    @Override
    public DashboardResponseDTO getFarmerSummary(Long farmerId) {
        List<Crop> crops = cropRepository.findByFarmerId(farmerId);

        List<ProfitResponseDTO> breakdown = crops.stream().map(crop -> {
            BigDecimal expense = expenseRepository.findByCropId(crop.getId()).stream()
                    .map(Expense::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal revenue = harvestRepository.findByCropId(crop.getId())
                    .map(h -> h.getQuantitySold().multiply(h.getPricePerUnit()))
                    .orElse(BigDecimal.ZERO);

            return ProfitResponseDTO.builder()
                    .cropName(crop.getCropName())
                    .totalExpense(expense)
                    .totalRevenue(revenue)
                    .netProfit(profitCalculator.calculateProfit(revenue, expense))
                    .build();
        }).collect(Collectors.toList());

        BigDecimal totalExpense = breakdown.stream().map(ProfitResponseDTO::getTotalExpense).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalRevenue = breakdown.stream().map(ProfitResponseDTO::getTotalRevenue).reduce(BigDecimal.ZERO, BigDecimal::add);

        return DashboardResponseDTO.builder()
                .farmerId(farmerId)
                .totalExpense(totalExpense)
                .totalRevenue(totalRevenue)
                .totalProfit(profitCalculator.calculateProfit(totalRevenue, totalExpense))
                .cropBreakdown(breakdown)
                .build();
    }
}
