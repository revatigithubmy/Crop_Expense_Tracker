package com.revati.farmersbuddy.service;

import com.revati.farmersbuddy.entity.Expense;

import java.util.List;

public interface ExpenseService {
    void addExpense(Long cropId, Expense expense);
    List<Expense> getExpensesByCrop(Long cropId);
}
