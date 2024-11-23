package com.vehicle.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import com.vehicle.project.repository.CustomerServiceReportRepository;

@Controller
public class CustomerReportController {

    @Autowired
    private CustomerServiceReportRepository customerServiceReportRepository;

    @GetMapping("/customer-frequency-report")
    public String getCustomerFrequencyReport(@RequestParam int year, @RequestParam int month, Model model) {
        
        List<Map<String, Object>> reportList = customerServiceReportRepository.getCustomerServiceReport(year, month);
        model.addAttribute("reportList", reportList);
        return "CustomerFrequencyReportView";
    }
}
