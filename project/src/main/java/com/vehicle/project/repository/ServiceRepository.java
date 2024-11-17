package com.vehicle.project.repository;

import com.vehicle.project.model.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<VehicleService> findAllServices() {
        String sql = "SELECT * FROM Service_table";
        List<VehicleService> services = jdbcTemplate.query(sql, (rs, rowNum) -> {
            VehicleService service = new VehicleService();
            service.setServiceId(rs.getInt("serviceId"));
            service.setServiceType(rs.getString("serviceType"));
            service.setBaseCost(rs.getBigDecimal("baseCost"));
            return service;
        });
        // Add logging to see the fetched services
        System.out.println("Fetched services: " + services);
        return services;
    }
}