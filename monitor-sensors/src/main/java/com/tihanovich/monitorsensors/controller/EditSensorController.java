package com.tihanovich.monitorsensors.controller;

import com.tihanovich.monitorsensors.model.Sensor;
import com.tihanovich.monitorsensors.service.IndicatorService;
import com.tihanovich.monitorsensors.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class EditSensorController {

    @Autowired
    private SensorService sensorService;

    @Autowired
    private IndicatorService indicatorService;

    @GetMapping("/edit-sensor/{id}")
    public String editSensorPage(@PathVariable("id") Integer id, Model model1, Model model2) {
        model1.addAttribute("sensor", sensorService.findSensorById(id));
        model2.addAttribute("indicators", indicatorService.findAllIndicators());
        return "edit-sensor";
    }

    @PostMapping("/edit-sensor/{id}")
    public String editSensor(@Valid @ModelAttribute("sensor") Sensor sensor, BindingResult bindingResult, Model model1,
                             Model model2, @PathVariable Integer id){
        model2.addAttribute("indicators", indicatorService.findAllIndicators());

        if (bindingResult.hasErrors()) {
            sensor.setSensorId(id);
            return "edit-sensor";
        }

        if (sensor.getSensorRangeFrom() >= sensor.getSensorRangeTo()) {
            String error = "The value FROM must be less TO";
            //добавить отображение indicatorov как вверух делал для гет
            model1.addAttribute("error", error);
            sensor.setSensorId(id);
            return "edit-sensor";
        }

        sensor.setSensorId(id);
        sensorService.saveSensor(sensor);
        return "redirect:/";
    }
}