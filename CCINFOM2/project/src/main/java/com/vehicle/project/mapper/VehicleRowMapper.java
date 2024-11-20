package com.vehicle.project.mapper;


import org.springframework.jdbc.core.RowMapper;
import com.vehicle.project.model.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;


public class VehicleRowMapper implements RowMapper<Vehicle>{
    @Override
    public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicle_id(rs.getInt("vehicle_id"));
        vehicle.setBrand(rs.getString("brand"));
        vehicle.setModel(rs.getString("model"));
        
        return vehicle;
    }
}
