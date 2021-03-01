package com.tihanovich.monitorsensors.repository;

import com.tihanovich.monitorsensors.model.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicatorRepository extends JpaRepository<Indicator, Integer> {
}
