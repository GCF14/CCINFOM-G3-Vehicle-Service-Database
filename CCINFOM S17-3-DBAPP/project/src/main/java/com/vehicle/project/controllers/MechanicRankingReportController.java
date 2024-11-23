package com.vehicle.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.vehicle.project.repository.MechanicRankingRepository;
import java.util.List;
import java.util.Map;

@Controller
public class MechanicRankingReportController {

    @Autowired
    private MechanicRankingRepository mechanicPerformanceRepository;

    @GetMapping("/mechanic-ranking-report")
    public String getMechanicPerformanceReport(@RequestParam int year, @RequestParam int month, Model model) {
        List<Map<String, Object>> reportData = mechanicPerformanceRepository.getMechanicPerformanceReport(year, month);

        model.addAttribute("yearMonth", year + "-" + String.format("%02d", month)); 
        model.addAttribute("reportData", reportData);

        return "MechanicRankingReportView"; 
    }
}
