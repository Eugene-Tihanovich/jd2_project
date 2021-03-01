package com.tihanovich.monitorsensors.service;

import com.tihanovich.monitorsensors.model.Indicator;
import com.tihanovich.monitorsensors.repository.IndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndicatorService {

    private final IndicatorRepository indicatorRepository;

    @Autowired
    public IndicatorService(IndicatorRepository indicatorRepository) {
        this.indicatorRepository = indicatorRepository;
    }

    public List<Indicator> findAllIndicators(){
        return indicatorRepository.findAll();
    }
}
