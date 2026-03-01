package com.revati.farmersbuddy.service.impl;

import com.revati.farmersbuddy.dto.request.ExpenseRequestDTO;
import com.revati.farmersbuddy.dto.response.ExpenseResponseDTO;
import com.revati.farmersbuddy.entity.Crop;
import com.revati.farmersbuddy.entity.Expense;
import com.revati.farmersbuddy.enums.ExpenseType;
import com.revati.farmersbuddy.repository.CropRepository;
import com.revati.farmersbuddy.repository.ExpenseRepository;
import com.revati.farmersbuddy.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CropRepository cropRepository;
    private final ModelMapper modelMapper;


    @Override
    public ExpenseResponseDTO addExpense(ExpenseRequestDTO requestDTO) {

        Crop crop = cropRepository.findById(requestDTO.getCropId())
                .orElseThrow(() -> new RuntimeException("Crop not found"));

        Expense expense = modelMapper.map(requestDTO, Expense.class);
        expense.setCrop(crop);

        Expense savedExpense = expenseRepository.save(expense);

        return convertToDTO(savedExpense);
    }


    @Override
    public List<ExpenseResponseDTO> getExpensesByCrop(Long cropId) {

        return expenseRepository.findByCropId(cropId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }


    @Override
    public ExpenseResponseDTO getExpenseById(Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        return convertToDTO(expense);
    }


    @Override
    public ExpenseResponseDTO updateExpense(Long id,
                                            ExpenseRequestDTO requestDTO) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        Crop crop = cropRepository.findById(requestDTO.getCropId())
                .orElseThrow(() -> new RuntimeException("Crop not found"));

        expense.setExpenseType(
                ExpenseType.valueOf(requestDTO.getExpenseType().toUpperCase())
        );

        expense.setAmount(requestDTO.getAmount());
        expense.setExpenseDate(requestDTO.getExpenseDate());
        expense.setCrop(crop);

        Expense updatedExpense = expenseRepository.save(expense);

        return convertToDTO(updatedExpense);
    }


    @Override
    public void deleteExpense(Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expenseRepository.delete(expense);
    }


    private ExpenseResponseDTO convertToDTO(Expense expense) {

        ExpenseResponseDTO dto =
                modelMapper.map(expense, ExpenseResponseDTO.class);

        dto.setCropId(expense.getCrop().getId());

        return dto;
    }
}