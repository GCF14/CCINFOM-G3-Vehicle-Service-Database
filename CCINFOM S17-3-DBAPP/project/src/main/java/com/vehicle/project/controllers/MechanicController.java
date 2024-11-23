package com.vehicle.project.controllers;

import com.vehicle.project.model.Mechanic;
import com.vehicle.project.repository.MechanicRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MechanicController {
    @Autowired
    private MechanicRepository mechanicRepository;

    @GetMapping("/api/mechanics")
    public List<Mechanic> getAllServices() {
        return mechanicRepository.findAll();
    }
}
