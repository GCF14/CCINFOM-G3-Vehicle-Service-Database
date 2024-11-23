package com.vehicle.project.mapper;

import com.vehicle.project.model.ServiceWithUpgrades;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicesWithUpgradesRowMapper implements RowMapper<ServiceWithUpgrades> {
    @Override
    public ServiceWithUpgrades mapRow(ResultSet rs, int rowNum) throws SQLException {
        ServiceWithUpgrades serviceWithUpgrades = new ServiceWithUpgrades();
        serviceWithUpgrades.setServiceType(rs.getString("service_type"));
        serviceWithUpgrades.setCustomerName(rs.getString("customer_name"));
        serviceWithUpgrades.setBrand(rs.getString("brand"));
        serviceWithUpgrades.setModel(rs.getString("model"));
        serviceWithUpgrades.setMechanicName(rs.getString("mechanic_name"));
        serviceWithUpgrades.setDateStart(rs.getString("date_start"));
        serviceWithUpgrades.setDateEnd(rs.getString("date_end"));
        serviceWithUpgrades.setStockName(rs.getString("name"));
        serviceWithUpgrades.setQuantityBought(rs.getInt("quantity_bought"));
        serviceWithUpgrades.setTotalCost(rs.getFloat("total_cost"));
        serviceWithUpgrades.setServiceRating(rs.getInt("service_rating"));
        return serviceWithUpgrades;
    }
}
