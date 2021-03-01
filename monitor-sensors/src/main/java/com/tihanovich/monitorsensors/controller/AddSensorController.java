package com.tihanovich.monitorsensors.controller;

import com.tihanovich.monitorsensors.model.Sensor;
import com.tihanovich.monitorsensors.service.IndicatorService;
import com.tihanovich.monitorsensors.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AddSensorController {

    @Autowired
    private SensorService sensorService;

    @Autowired
    private IndicatorService indicatorService;

    @GetMapping("/add-sensor")
    public String addPage(Model model1, Model model2) {
        model1.addAttribute("sensor", new Sensor());
        model2.addAttribute("indicators", indicatorService.findAllIndicators());
        return "add-sensor";
    }

    @PostMapping("/add-sensor")
    public String addSensor(@Valid @ModelAttribute("sensor") Sensor sensor, BindingResult bindingResult, Model model1,
                            Model model2) {
        model2.addAttribute("indicators", indicatorService.findAllIndicators());

        if (bindingResult.hasErrors()) {
            return "add-sensor";
        }

        if (sensor.getSensorRangeFrom() >= sensor.getSensorRangeTo()) {
            String error = "The value FROM must be less TO";
            model1.addAttribute("error", error);
            return "add-sensor";
        }

        sensorService.saveSensor(sensor);
        return "redirect:/";
    }
}
