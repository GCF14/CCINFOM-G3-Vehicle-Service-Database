package com.vehicle.project.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {

    

    @GetMapping(value = "/")
    public String home() {
        return "redirect:/index.html"; // This will resolve to /WEB-INF/views/homepage.jsp
    }
}
