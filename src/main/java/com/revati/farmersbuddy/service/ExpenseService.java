package com.revati.farmersbuddy.service;

import com.revati.farmersbuddy.dto.request.ExpenseRequestDTO;
import com.revati.farmersbuddy.dto.response.ExpenseResponseDTO;
import com.revati.farmersbuddy.entity.Expense;


import java.util.List;

public interface ExpenseService {
    ExpenseResponseDTO addExpense(ExpenseRequestDTO requestDTO);

    List<ExpenseResponseDTO> getExpensesByCrop(Long cropId);

    ExpenseResponseDTO getExpenseById(Long id);

    ExpenseResponseDTO updateExpense(Long id, ExpenseRequestDTO requestDTO);

    void deleteExpense(Long id);
}
