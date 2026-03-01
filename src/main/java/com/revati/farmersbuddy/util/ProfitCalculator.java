package com.revati.farmersbuddy.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ProfitCalculator {
    public BigDecimal calculateRevenue(BigDecimal quantity, BigDecimal pricePerUnit) {
        if (quantity == null || pricePerUnit == null) {
            return BigDecimal.ZERO;
        }
        return quantity.multiply(pricePerUnit);
    }

    public BigDecimal calculateTotalExpense(List<BigDecimal> expenses) {
        if (expenses == null || expenses.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return expenses.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateProfit(BigDecimal revenue, BigDecimal expense) {
        return revenue.subtract(expense);
    }
}
