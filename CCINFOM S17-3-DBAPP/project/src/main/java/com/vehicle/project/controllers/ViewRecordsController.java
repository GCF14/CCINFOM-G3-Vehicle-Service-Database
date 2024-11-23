package com.vehicle.project.controllers;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vehicle.project.repository.ViewRepository;


import org.springframework.ui.Model;

@Controller
public class ViewRecordsController {

    @Autowired
    private ViewRepository viewRepository;

    @GetMapping("/view-records")
    public String getMethodName(@RequestParam String viewSelect, Model model) {
        System.out.println("Received request for viewSelect: " + viewSelect);

        String viewName = "";
        try {
            List<?> viewData = null;  
            if ("service_history_view".equals(viewSelect)) {
                viewData = viewRepository.findServiceHistoryViews();
                viewName = "Service History View";

                model.addAttribute("viewData", viewData);
                model.addAttribute("viewName", viewName);

                return "serviceHistoryView";
            } else if ("customer_and_vehicle".equals(viewSelect)) {
                viewData = viewRepository.getCustomerAndVehicleView();
                viewName = "Customer and Vehicle View";

                model.addAttribute("viewData", viewData);
                model.addAttribute("viewName", viewName);

                return "customerAndVehicleView";
            } else if ("mechanics".equals(viewSelect)) {
                viewData = viewRepository.getMechanicsView();
                viewName = "Mechanics View";

                model.addAttribute("viewData", viewData);
                model.addAttribute("viewName", viewName);

                return "mechanicsView";
            } else if ("stocks".equals(viewSelect)) {
                viewData = viewRepository.getStocksView();
                viewName = "Stocks View";

                model.addAttribute("viewData", viewData);
                model.addAttribute("viewName", viewName);

                return "stocksView";
            } else if ("services".equals(viewSelect)) {
                viewData = viewRepository.getServicesView();
                viewName = "Services View";

                model.addAttribute("viewData", viewData);
                model.addAttribute("viewName", viewName);

                return "servicesView";
            } else if ("services_with_upgrades".equals(viewSelect)) {
                viewData = viewRepository.getServicesWithUpgradesView();
                viewName = "Services with Upgrades View";

                model.addAttribute("viewData", viewData);
                model.addAttribute("viewName", viewName);

                return "servicesWithUpgradesView";
            } else if ("customers".equals(viewSelect)) {
                viewData = viewRepository.getCustomersView();
                viewName = "Customers View";

                model.addAttribute("viewData", viewData);
                model.addAttribute("viewName", viewName);

                return "customersView";
            } else if ("working_mechanics".equals(viewSelect)) {
                viewData = viewRepository.getWorkingMechanicsView();
                viewName = "Working Mechanics View";

                model.addAttribute("viewData", viewData);
                model.addAttribute("viewName", viewName);

                return "workingMechanicsView";
            } else {
                return "redirect:/error.html?errorMessage=" + URLEncoder.encode("Invalid view selected.", StandardCharsets.UTF_8);
            }

            
            
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "redirect:/error.html?errorMessage=" + URLEncoder.encode("Invalid view selected.", StandardCharsets.UTF_8);
            
        }
    }
}

