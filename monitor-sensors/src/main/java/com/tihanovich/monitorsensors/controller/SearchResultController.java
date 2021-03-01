package com.tihanovich.monitorsensors.controller;

import com.tihanovich.monitorsensors.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SearchResultController {

    @Autowired
    private SensorRepository sensorRepository;


    @GetMapping("/search")
    public String searchResult(@RequestParam(value = "param", required = false) String param, Model model,
                              Model model2, HttpServletRequest request){
        int page = 0;
        int size = 4;

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        if (param != null && !"".equals(param.trim())) {
            model.addAttribute("sensors", sensorRepository.searchAll(param, PageRequest.of(page, size)));
            model2.addAttribute("text", param);
            return "search-result";
        }
        return "redirect:/";
    }
}