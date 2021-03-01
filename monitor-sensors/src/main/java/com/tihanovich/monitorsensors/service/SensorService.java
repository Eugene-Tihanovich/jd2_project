package com.tihanovich.monitorsensors.service;

import com.tihanovich.monitorsensors.model.Sensor;
import com.tihanovich.monitorsensors.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public void saveSensor(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public void deleteSensorById(Integer id) {
        sensorRepository.deleteById(id);
    }

    public Sensor findSensorById(Integer id) {
        return sensorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid sensor id:" + id));
    }
}