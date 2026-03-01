package com.revati.farmersbuddy.repository;

import com.revati.farmersbuddy.entity.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HarvestRepository extends JpaRepository<Harvest,Long> {

    Optional<Harvest> findByCropId(Long cropId);

}
