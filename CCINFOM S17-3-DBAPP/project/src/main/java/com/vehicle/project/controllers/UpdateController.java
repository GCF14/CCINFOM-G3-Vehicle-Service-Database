package com.vehicle.project.controllers;



import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vehicle.project.repository.UpdateRepository;
import com.vehicle.project.exception.ErrorException;
import java.net.URLEncoder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;


@Controller
public class UpdateController {

    @Autowired
    private JdbcTemplate jdbcTemplate; 

    @Autowired
    private UpdateRepository updateRepository;

    private String savedTable; 
    private int savedId; 

    @GetMapping("/update")
    public String handleTableSelection(@RequestParam("tableSelectUpdate") String table,
                                        @RequestParam("recordSelectUpdate") int id) {
        
        this.savedTable = table;
        this.savedId = id;

        switch(table) {
            case "Customer_table":
                return "redirect:/CustomerUpdate.html";
            case "Service_history_table":
                return "redirect:/ServiceHistoryUpdate.html";
            case "Vehicle_table":
                return "redirect:/VehicleUpdate.html";
            case "Service_table":
                return "redirect:/ServiceUpdate.html";
            case "Mechanic_table":
                return "redirect:/MechanicUpdate.html";
            case "Stock_table":
                return "redirect:/StockUpdate.html";
            case "Stock_usage_table":
                return "redirect:/StockUsageUpdate.html";
        }

        return "success";

    }

    @PostMapping("/customer-update")
    public String handleCustomerUpdate(@RequestParam("firstName") String firstName,
                                       @RequestParam("lastName") String lastName,
                                       @RequestParam("contactDetails") String contactDetails) {
        
        if ("Customer_table".equals(savedTable)) {
            try {
                // Use the injected repository to perform the update
                updateRepository.updateCustomer(savedId, firstName, lastName, contactDetails);
                return "redirect:/success.html";
            } catch (Exception e) {
                return "redirect:/error.html?errorMessage=" + 
                       URLEncoder.encode("An unexpected error occurred.", StandardCharsets.UTF_8);
            }
        }

        return "redirect:/error.html?errorMessage=" + 
               URLEncoder.encode("An unexpected error occurred.", StandardCharsets.UTF_8);
    }


    @PostMapping("/service-history-update")
    public String handleServiceHistoryUpdate(@RequestParam("currentDate") String currentDate,
                                            @RequestParam("endDate") String endDate,
                                            @RequestParam(value = "rating", required = false) Integer rating) {
                                                
        
        try {
            
            if (currentDate == null || currentDate.isEmpty()) {
                
                if (endDate != null && !endDate.isEmpty() && rating != null) {
                    return "redirect:/error.html?errorMessage=" + 
                        URLEncoder.encode("Start date (currentDate) is required when end date and rating are provided.", StandardCharsets.UTF_8);
                }
                
                return "redirect:/error.html?errorMessage=" + 
                    URLEncoder.encode("Current date is required.", StandardCharsets.UTF_8);
            }

            
            LocalDate startDate = LocalDate.parse(currentDate);

            
            LocalDate finishDate = endDate.isEmpty() ? null : LocalDate.parse(endDate);

            
            if (finishDate == null && rating == null) {
                try {
                    updateRepository.updateServiceHistory(savedId, startDate.toString());
                    return "redirect:/success.html";
                } catch (Exception e) {
                    return "redirect:/error.html?errorMessage=" + 
                            URLEncoder.encode("An unexpected error occurred while updating the start date.", StandardCharsets.UTF_8);
                }
            }

           
            if (finishDate != null && rating != null) {
            
                if (finishDate.isBefore(startDate)) {
                    return "redirect:/error.html?errorMessage=" + 
                            URLEncoder.encode("End date cannot be earlier than the start date.", StandardCharsets.UTF_8);
                }
                try {
                    updateRepository.updateServiceHistory(savedId, startDate.toString(), finishDate.toString(), rating);
                    return "redirect:/success.html";
                } catch (Exception e) {
                    return "redirect:/error.html?errorMessage=" + 
                            URLEncoder.encode("An unexpected error occurred while updating the service history.", StandardCharsets.UTF_8);
                }
            }

           
            return "redirect:/error.html?errorMessage=" + 
                    URLEncoder.encode("Invalid data or missing required fields.", StandardCharsets.UTF_8);
        } catch (DateTimeParseException e) {
           
            return "redirect:/error.html?errorMessage=" + 
                    URLEncoder.encode("Invalid date format. Please check the dates entered.", StandardCharsets.UTF_8);
        }
    }


    @PostMapping("/mechanic-update")
    public String handleMechanicUpdate(@RequestParam("firstName") String firstName,
                                    @RequestParam("lastName") String lastName,
                                    @RequestParam("hireDate") String hireDate,
                                    @RequestParam("endDate") String endDate) {
        try {
            
            LocalDate DateStart = LocalDate.parse(hireDate);
            LocalDate DateEnd = LocalDate.parse(endDate);

            if (DateEnd.isBefore(DateStart)) {
                return "redirect:/error.html?errorMessage=" + 
                    URLEncoder.encode("End date cannot be earlier than hire date.", StandardCharsets.UTF_8);
            }

            
            if (firstName == null || firstName.isEmpty()) {
                updateRepository.updateMechanic2(savedId, lastName, hireDate, endDate);
            } else if (lastName == null || lastName.isEmpty()) {
                updateRepository.updateMechanic(savedId, firstName, hireDate, endDate);
            } else {
                updateRepository.updateMechanic3(savedId, firstName, lastName, hireDate, endDate);
            }

            return "redirect:/success.html";

        } catch (DateTimeParseException e) {
            return "redirect:/error.html?errorMessage=" + 
                URLEncoder.encode("Invalid date format. Please check your input.", StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "redirect:/error.html?errorMessage=" + 
                URLEncoder.encode("An unexpected error occurred: " + e.getMessage(), StandardCharsets.UTF_8);
        }
    }

    @PostMapping("/vehicle-update")
    public String handleVehicleUpdate(@RequestParam("brand") String brand,
                                    @RequestParam("model") String model,
                                    @RequestParam("year") String year) {
        try {
            

            
            if (brand == null || brand.isEmpty()) {
                updateRepository.updateVehicle3(savedId, model, year);
            } else if (model == null || model.isEmpty()) {
                updateRepository.updateVehicle2(savedId, brand, year);
            } else {
                updateRepository.updateVehicle(savedId, brand, model, year);
            }

            return "redirect:/success.html";

        } catch (Exception e) {
            return "redirect:/error.html?errorMessage=" + 
                URLEncoder.encode("An unexpected error occurred: " + e.getMessage(), StandardCharsets.UTF_8);
        }
    }

    @PostMapping("/stock-update")
    public String handleStockUpdate(
            @RequestParam("name") String name,
            @RequestParam(value = "price", required = false) Float price,
            @RequestParam("manufacturingDate") String manufacturingDate,
            @RequestParam("warranty") String warranty) {

        try {
            // Ensure 'savedId' is initialized correctly in your context
            if (name == null || name.isEmpty()) {
                updateRepository.updateStock3(savedId, price, manufacturingDate, warranty);
            } else if (price == null) {
                updateRepository.updateStock2(savedId, name, manufacturingDate, warranty);
            } else if ((name != null && !name.isEmpty()) && (price != null && price >= 0)) {
                updateRepository.updateStock(savedId, name, price, manufacturingDate, warranty);
            } else {
                return "redirect:/error.html?errorMessage=Invalid parameters";
            }

            return "redirect:/success.html";

        } catch (Exception e) {
            return "redirect:/error.html?errorMessage=" +
                    URLEncoder.encode("An unexpected error occurred: " + e.getMessage(), StandardCharsets.UTF_8);
        }
    }

    @PostMapping("/service-update")
    public String handleServiceUpdate(
            @RequestParam("type") String name,
            @RequestParam(value = "price", required = false) Float price) {

        try {
            // Ensure 'savedId' is initialized correctly in your context
            if (name == null || name.isEmpty()) {
                updateRepository.updateService(savedId, price);
            } else if (price == null) {
                updateRepository.updateService2(savedId, name);
            } else if ((name != null && !name.isEmpty()) && (price != null && price >= 0)) {
                updateRepository.updateService3(savedId, name, price);
            } else {
                return "redirect:/error.html?errorMessage=Invalid parameters";
            }

            return "redirect:/success.html";

        } catch (Exception e) {
            return "redirect:/error.html?errorMessage=" +
                    URLEncoder.encode("An unexpected error occurred: " + e.getMessage(), StandardCharsets.UTF_8);
        }
    }




    @PostMapping("/stock-usage-update")
    public String handleStockUsageUpdate(
            @RequestParam("quantity") int quantity) {

        try {
            updateRepository.updateStockUsage(savedId, quantity);
            
            return "redirect:/success.html";

        } catch (Exception e) {
            return "redirect:/error.html?errorMessage=" +
                    URLEncoder.encode("An unexpected error occurred: " + e.getMessage(), StandardCharsets.UTF_8);
        }
    }




}
