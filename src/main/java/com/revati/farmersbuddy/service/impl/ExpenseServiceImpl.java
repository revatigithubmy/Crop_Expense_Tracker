package com.revati.farmersbuddy.service.impl;

import com.revati.farmersbuddy.entity.Crop;
import com.revati.farmersbuddy.entity.Expense;
import com.revati.farmersbuddy.repository.CropRepository;
import com.revati.farmersbuddy.repository.ExpenseRepository;
import com.revati.farmersbuddy.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CropRepository cropRepository;

    @Override
    public void addExpense(Long cropId, Expense expense) {

        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new RuntimeException("Crop not found"));

        expense.setCrop(crop);

        expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getExpensesByCrop(Long cropId) {
        return expenseRepository.findByCropId(cropId);
    }

}
