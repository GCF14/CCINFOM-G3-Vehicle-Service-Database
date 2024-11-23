package com.vehicle.project.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.vehicle.project.exception.ErrorException;
import com.vehicle.project.mapper.StockRowMapper;
import com.vehicle.project.model.Stock;


@Repository
public class StockRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<Stock> findAll() {
        String sql = "SELECT * FROM Stock_table"; 
        return jdbcTemplate.query(sql, new StockRowMapper());
    }

    public int add(Stock stock) {
        String sqlChecker = "SELECT COUNT(*) FROM Stock_table WHERE name = ?";
        int count = jdbcTemplate.queryForObject(sqlChecker, Integer.class, stock.getName());

        if(count == 0 && stock.getPrice() >= 0){
            String sql = "INSERT INTO Stock_table(name, price, manufacturing_date, warranty) VALUES (?, ?, ?, ?)";
            return jdbcTemplate.update(sql, stock.getName(), stock.getPrice(), stock.getManufacturingDate(), stock.getWarranty());
        } else if(stock.getPrice() < 0){
            throw new ErrorException("Price cannot be below ₱0");
        } else {
            throw new ErrorException("The item is already in the inventory.");
        }
    }
}
