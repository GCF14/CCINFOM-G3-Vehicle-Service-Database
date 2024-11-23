package com.vehicle.project.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vehicle.project.model.Mechanic;

public class ViewMechanicRowMapper implements RowMapper<Mechanic>{
    
    @Override
    public Mechanic mapRow(ResultSet rs, int rowNum) throws SQLException {
        Mechanic mechanic = new Mechanic();
        mechanic.setFirstName(rs.getString("mechanic_first_name"));
        mechanic.setLastName(rs.getString("mechanic_last_name"));
        mechanic.setHireDate(rs.getString("hire_date"));
        mechanic.setEndDate(rs.getString("end_date"));
        return mechanic;
    }
}
