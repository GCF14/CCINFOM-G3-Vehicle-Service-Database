package com.vehicle.project.controllers;

import com.vehicle.project.model.Service;
import com.vehicle.project.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/api/services")
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

}
