package com.vehicle.project.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.vehicle.project.model.Stock;



import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewStockRowMapper implements RowMapper<Stock>{
    @Override
    public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
        Stock stock = new Stock();
        stock.setName(rs.getString("name"));
        stock.setPrice(rs.getFloat("price"));
        stock.setManufacturingDate(rs.getString("manufacturing_date"));
        stock.setWarranty(rs.getString("warranty"));
        return stock;
    }
}
