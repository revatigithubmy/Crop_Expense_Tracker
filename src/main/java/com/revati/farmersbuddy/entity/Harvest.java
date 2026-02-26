package com.revati.farmersbuddy.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "harvests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Harvest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal quantitySold;

    @Column(nullable = false)
    private BigDecimal pricePerUnit;

    @OneToOne
    @JoinColumn(name = "crop_id", nullable = false, unique = true)
    private Crop crop;

}
