package com.revati.farmersbuddy.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseRequestDTO {

    private String expenseType;
    private BigDecimal amount;
    private LocalDate expenseDate;
    private Long cropId;
}
