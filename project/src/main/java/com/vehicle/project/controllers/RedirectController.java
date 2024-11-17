package com.vehicle.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class RedirectController {

    @GetMapping("/modify")
    public String modifyRecords() {
        return "modify"; // Resolves to /WEB-INF/views/modifyRecords.jsp
    }

    @GetMapping("/form")
    public String createService() {
        return "form"; // Resolves to /WEB-INF/views/createService.jsp
    }

    @GetMapping("/tables")
    public String viewRecords() {
        return "tables"; // Resolves to /WEB-INF/views/viewRecords.jsp
    }
}
