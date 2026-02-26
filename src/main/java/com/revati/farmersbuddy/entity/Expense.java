package com.revati.farmersbuddy.entity;

import com.revati.farmersbuddy.enums.ExpenseType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;

    @Column(nullable = false)
    private BigDecimal amount;

    private LocalDate expenseDate;

    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    private Crop crop;

}
