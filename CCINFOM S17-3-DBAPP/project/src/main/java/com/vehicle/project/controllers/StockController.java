package com.vehicle.project.controllers;


import com.vehicle.project.model.Stock;
import com.vehicle.project.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockController {
    @Autowired
    private StockRepository stockRepository;

    @GetMapping("/api/stocks")
    public List<Stock> getAllServices() {
        return stockRepository.findAll();
    }
}
