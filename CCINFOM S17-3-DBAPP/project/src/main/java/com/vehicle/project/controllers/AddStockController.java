package com.vehicle.project.controllers;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.vehicle.project.repository.StockRepository;
import com.vehicle.project.exception.ErrorException;
import com.vehicle.project.model.Stock;
import java.net.URLEncoder;

@Controller
public class AddStockController {

    @Autowired
    private StockRepository stockRepository;

    @GetMapping("/submit-stock")
    public String addItem(
    @RequestParam("name") String name,
    @RequestParam("price") String price,
    @RequestParam("manufactured") String manufactured,
    @RequestParam("warranty") String warranty) {
        
        Stock stock = new Stock();

        stock.setName(name);
        stock.setPrice(Float.parseFloat(price));
        stock.setManufacturingDate(manufactured);
        stock.setWarranty(warranty);
        
        try {
            stockRepository.add(stock);

        } catch(ErrorException e) {
            return "redirect:/error.html?errorMessage=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
        }
        
        return "redirect:/success.html?firstname=";
    }
    
    
}
