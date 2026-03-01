package com.revati.farmersbuddy.controller;

import com.revati.farmersbuddy.dto.request.ExpenseRequestDTO;
import com.revati.farmersbuddy.dto.response.ExpenseResponseDTO;
import com.revati.farmersbuddy.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;


    @PostMapping
    public ExpenseResponseDTO addExpense(
            @RequestBody ExpenseRequestDTO requestDTO) {

        return expenseService.addExpense(requestDTO);
    }


    @GetMapping("/crop/{cropId}")
    public List<ExpenseResponseDTO> getExpensesByCrop(
            @PathVariable Long cropId) {

        return expenseService.getExpensesByCrop(cropId);
    }


    @GetMapping("/{id}")
    public ExpenseResponseDTO getExpenseById(
            @PathVariable Long id) {

        return expenseService.getExpenseById(id);
    }


    @PutMapping("/{id}")
    public ExpenseResponseDTO updateExpense(
            @PathVariable Long id,
            @RequestBody ExpenseRequestDTO requestDTO) {

        return expenseService.updateExpense(id, requestDTO);
    }


    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable Long id) {

        expenseService.deleteExpense(id);
        return "Expense deleted successfully";
    }

}
