package com.vehicle.project.controllers;

import com.vehicle.project.model.VehicleService; // Import the VehicleService model
import com.vehicle.project.services.ServiceService; // Import the ServiceService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Indicates that this class is a REST controller
@RequestMapping("/api/services") // Base URL for all methods in this controller
public class ServiceController {

    @Autowired // Automatically injects the ServiceService bean
    private ServiceService serviceService;

    @GetMapping // Handles GET requests to /api/services
    public List<VehicleService> getAllServices() {
        return serviceService.getAllServices(); // Calls the service to fetch all services
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "Test endpoint is working!";
    }
}