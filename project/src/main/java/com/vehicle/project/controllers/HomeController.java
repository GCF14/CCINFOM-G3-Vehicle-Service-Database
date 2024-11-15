package com.vehicle.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.vehicle.project.services.DatabaseService;

@Controller
public class HomeController {

     @Autowired
     private DatabaseService databaseService;  // Inject the service

    @GetMapping(value = "/")
    public String home() {
        databaseService.insertCustomerData();  // Call the method to insert data
        return "redirect:/index.html"; // This will resolve to /WEB-INF/views/homepage.jsp
    }
}
