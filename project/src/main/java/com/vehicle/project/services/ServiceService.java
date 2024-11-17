package com.vehicle.project.services;

import com.vehicle.project.model.VehicleService;
import com.vehicle.project.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    public List<VehicleService> getAllServices() {
        return serviceRepository.findAllServices(); // Use the correct method name
    }
}