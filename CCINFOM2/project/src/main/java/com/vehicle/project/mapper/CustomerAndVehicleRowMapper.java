package com.vehicle.project.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vehicle.project.model.CustomerAndVehicle;
import org.springframework.jdbc.core.RowMapper;

public class CustomerAndVehicleRowMapper implements RowMapper<CustomerAndVehicle> {
     @Override
     public CustomerAndVehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
         CustomerAndVehicle vehicle = new CustomerAndVehicle();
         vehicle.setLastName(rs.getString("last_name"));
         vehicle.setFirstName(rs.getString("first_name"));
         vehicle.setContactDetails(rs.getString("contact_details"));
         vehicle.setBrand(rs.getString("brand"));
         vehicle.setModel(rs.getString("model"));
         vehicle.setYearMade(rs.getInt("year_made"));
         return vehicle;
     }
}
