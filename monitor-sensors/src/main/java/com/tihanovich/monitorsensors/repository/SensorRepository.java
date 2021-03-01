package com.tihanovich.monitorsensors.repository;

import com.tihanovich.monitorsensors.model.Sensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    @Query("SELECT s from Sensor s where s.sensorName like %?1%" +
            "or s.sensorModel like %?1%" +
            "or CONCAT(s.sensorRangeFrom, '') like %?1%" +
            "or CONCAT(s.sensorRangeTo, '') like %?1%" +
            "or s.sensorLocation like %?1%" +
            "or s.sensorDescription like %?1%" +
            "or s.idFromIndicator.indicatorType like %?1%" +
            "or s.idFromIndicator.indicatorUnit like %?1%")
    Page<Sensor> searchAll(String param, Pageable pageable);

}