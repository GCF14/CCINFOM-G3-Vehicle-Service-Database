package com.vehicle.project.controllers;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.vehicle.project.repository.SubmitRepository;
import com.vehicle.project.exception.ErrorException;
import com.vehicle.project.model.Submit;
import org.springframework.ui.Model;
import java.net.URLEncoder;

@Controller
public class SubmitController {

    @Autowired
    private SubmitRepository submitRepository;

    @GetMapping("/submit-service")
    public String submitService(
        @RequestParam("firstname") String firstname,
        @RequestParam("lastname") String lastname, 
        @RequestParam("phone") String phone,
        @RequestParam("vehiclebrand") String vehiclebrand,
        @RequestParam("vehiclemodel") String vehiclemodel,
        @RequestParam("year") String manufacturedYear,
        @RequestParam("date") String date,
        @RequestParam("serviceSelect") String service,
        @RequestParam("mechanicSelect") String mechanic,
        @RequestParam(value = "partSelect", defaultValue = "0") String part,
        @RequestParam(value = "partQuantity", required = false, defaultValue = "0") int partQuantity,
        Model model) {

        Submit submit = new Submit();
        submit.setFirstName(firstname);
        submit.setLastName(lastname);
        submit.setPhone(phone);
        submit.setBrand(vehiclebrand);  
        submit.setModel(vehiclemodel); 
        submit.setYearMade(manufacturedYear);       
        submit.setDate(date);          
        submit.setService(Integer.parseInt(service));  
        submit.setMechanic(Integer.parseInt(mechanic));
        submit.setStock(Integer.parseInt(part));        
        submit.setQuantity(partQuantity);

        try {
            submitRepository.saveHistory(submit);
        } catch (ErrorException e) {
            return "redirect:/error.html?errorMessage=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "redirect:/error.html?errorMessage=" + URLEncoder.encode("An unexpected error occurred.", StandardCharsets.UTF_8);
        }

        return "redirect:/success.html?firstname=" + firstname;
    }
}


