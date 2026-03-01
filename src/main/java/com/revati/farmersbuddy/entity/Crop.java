package com.revati.farmersbuddy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(nullable = false)
    private Double landArea;

    @Column(nullable = false)
    private LocalDate sowingDate;

    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    @JsonIgnore
    private Farmer farmer;

    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Expense> expenses;

    @OneToOne(mappedBy = "crop", cascade = CascadeType.ALL)
    @JsonIgnore
    private Harvest harvest;



}
