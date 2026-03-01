package com.revati.farmersbuddy.service.impl;

import com.revati.farmersbuddy.dto.response.DashboardResponseDTO;
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

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final CropRepository cropRepository;
    private final ExpenseRepository expenseRepository;
    private final HarvestRepository harvestRepository;
    private final ProfitCalculator profitCalculator;

    // Crop Summary
    @Override
    public DashboardResponseDTO getCropSummary(Long cropId) {

        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found"));

        List<Expense> expenses = expenseRepository.findByCropId(cropId);

        BigDecimal totalExpense = expenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Harvest harvest = harvestRepository.findByCropId(cropId)
                .orElseThrow(() -> new ResourceNotFoundException("Harvest not found"));

        BigDecimal totalRevenue = harvest.getQuantitySold()
                .multiply(harvest.getPricePerUnit());

        BigDecimal totalProfit =
                profitCalculator.calculateProfit(totalRevenue, totalExpense);

        return DashboardResponseDTO.builder()
                .cropId(cropId)
                .farmerId(crop.getFarmer().getId())
                .totalExpense(totalExpense)
                .totalRevenue(totalRevenue)
                .totalProfit(totalProfit)
                .build();
    }

    @Override
    public DashboardResponseDTO getFarmerSummary(Long farmerId) {

        List<Crop> crops = cropRepository.findByFarmerId(farmerId);

        if (crops.isEmpty()) {
            throw new ResourceNotFoundException("No crops found for farmer");
        }

        // Total Expense
        BigDecimal totalExpense = crops.stream()
                .map(crop -> expenseRepository.findByCropId(crop.getId())
                        .stream()
                        .map(Expense::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Total Revenue
        BigDecimal totalRevenue = crops.stream()
                .map(crop -> harvestRepository.findByCropId(crop.getId())
                        .map(harvest -> harvest.getQuantitySold()
                                .multiply(harvest.getPricePerUnit()))
                        .orElse(BigDecimal.ZERO))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalProfit =
                profitCalculator.calculateProfit(totalRevenue, totalExpense);

        return DashboardResponseDTO.builder()
                .farmerId(farmerId)
                .totalExpense(totalExpense)
                .totalRevenue(totalRevenue)
                .totalProfit(totalProfit)
                .build();
    }
}
