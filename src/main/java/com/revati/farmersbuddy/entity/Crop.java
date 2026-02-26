package com.revati.farmersbuddy.entity;

import com.revati.farmersbuddy.enums.Season;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "crops")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cropName;

    @Enumerated(EnumType.STRING)
    private Season season;

    private Double landArea;

    private LocalDate sowingDate;

    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    private Farmer farmer;

    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses;

    @OneToOne(mappedBy = "crop", cascade = CascadeType.ALL)
    private Harvest harvest;



}
