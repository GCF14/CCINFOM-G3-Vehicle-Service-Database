package com.vehicle.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class RedirectController {

    @GetMapping("/modify")
    public String modifyRecords() {
        return "modify"; 
    }

    @GetMapping("/form")
    public String createService() {
        return "form"; 
    }

    @GetMapping("/tables")
    public String viewRecords() {
        return "tables"; 
    }
}
