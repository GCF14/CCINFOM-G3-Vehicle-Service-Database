package com.vehicle.project.mapper;


import org.springframework.jdbc.core.RowMapper;

import com.vehicle.project.model.StockUsage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StockUsageRowMapper implements RowMapper<StockUsage>{

    @Override
    public StockUsage mapRow(ResultSet rs, int rowNum) throws SQLException {
        StockUsage stockUsage = new StockUsage();
        stockUsage.setStockUsageId(rs.getInt("stock_usage_id"));
        stockUsage.setService_history_id(rs.getInt("service_history_id"));
        stockUsage.setServiceType(rs.getString("service_type"));
        stockUsage.setStock_item(rs.getString("name"));
        stockUsage.setQuantity(rs.getInt("quantity"));
        return stockUsage;
    }
    
}
