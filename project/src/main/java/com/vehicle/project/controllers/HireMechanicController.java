package com.vehicle.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.vehicle.project.model.Mechanic;
import com.vehicle.project.repository.MechanicRepository;
import com.vehicle.project.exception.ErrorException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Controller
public class HireMechanicController {

    @Autowired
    private MechanicRepository mechanicRepository;

    @GetMapping("/submit-mechanic")
    public String hire(
        @RequestParam("firstName") String firstName,
        @RequestParam("lastName") String lastName,
        @RequestParam("hireDate") String hireDate,
        @RequestParam("endDate") String endDate) {


            Mechanic mechanic = new Mechanic();

            mechanic.setFirstName(firstName);
            mechanic.setLastName(lastName);
            mechanic.setHireDate(hireDate);
            mechanic.setEndDate(endDate);

            try {
                mechanicRepository.hireMechanic(mechanic);
            } catch (ErrorException e){
                return "redirect:/error.html?errorMessage=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
            }

            return "redirect:/success.html?firstname=";
    }
    
}
