package com.tihanovich.monitorsensors.controller;

import com.tihanovich.monitorsensors.repository.SensorRepository;
import com.tihanovich.monitorsensors.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @Autowired
    private SensorRepository sensorRepository;

    @GetMapping()
    public String tablePage(Model model, HttpServletRequest request){
        int page = 0;
        int size = 4;

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("sensors", sensorRepository.findAll(PageRequest.of(page, size)));
        return "sensors-table";
    }

    @PostMapping()
    public String deleteSensor(@RequestParam(name = "deleteId") Integer id){
        sensorService.deleteSensorById(id);
        return "redirect:/";
    }
}
