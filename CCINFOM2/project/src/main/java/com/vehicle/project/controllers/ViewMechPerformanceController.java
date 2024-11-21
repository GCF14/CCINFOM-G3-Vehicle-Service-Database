package com.vehicle.project.controllers;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vehicle.project.repository.MechanicPerformanceReportRepository;
import com.vehicle.project.model.Mechanic;
import com.vehicle.project.model.ServiceHistoryView;

import org.springframework.ui.Model;

@Controller
public class ViewMechPerformanceController {

    @Autowired
    private MechanicPerformanceReportRepository mechanicPerformanceReportRepository;

    
    @GetMapping("/view-mechperfreport")
    public String viewMechanicPerformanceReport(
            @RequestParam("year-month") String yearMonth, 
            Model model) {
        
        // Fetch data from the repository
        List<Map<String, Object>> reportData = mechanicPerformanceReportRepository.findMechanicPerformanceByYearMonth(yearMonth);

        // Add data to the model
        model.addAttribute("reportData", reportData);
        model.addAttribute("yearMonth", yearMonth);

        // Return the name of the view template
        return "MechanicPerformanceReportView";
    }
}

