package com.revati.farmersbuddy.controller;

import com.revati.farmersbuddy.entity.Expense;
import com.revati.farmersbuddy.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/add/{cropId}")
    public String addExpense(@PathVariable Long cropId,
                             @RequestBody Expense expense) {

        expenseService.addExpense(cropId, expense);
        return "Expense added successfully";
    }

    @GetMapping("/crop/{cropId}")
    public List<Expense> getExpenses(@PathVariable Long cropId) {
        return expenseService.getExpensesByCrop(cropId);
    }


}
