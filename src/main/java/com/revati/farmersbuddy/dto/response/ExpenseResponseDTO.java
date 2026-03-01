package com.revati.farmersbuddy.dto.response;

import com.revati.farmersbuddy.enums.ExpenseType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseResponseDTO {

    private Long id;

    private ExpenseType expenseType;

    private BigDecimal amount;

    private LocalDate expenseDate;

    private Long cropId;

}
