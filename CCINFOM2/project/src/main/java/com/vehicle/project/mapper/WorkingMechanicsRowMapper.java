package com.vehicle.project.mapper;

import com.vehicle.project.model.WorkingMechanic;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkingMechanicsRowMapper implements RowMapper<WorkingMechanic> {
    @Override
    public WorkingMechanic mapRow(ResultSet rs, int rowNum) throws SQLException {
        WorkingMechanic mechanic = new WorkingMechanic();
        mechanic.setMechanicLastName(rs.getString("mechanic_last_name"));
        mechanic.setMechanicFirstName(rs.getString("mechanic_first_name"));
        mechanic.setBrand(rs.getString("brand"));
        mechanic.setModel(rs.getString("model"));
        mechanic.setServiceType(rs.getString("service_type"));
        mechanic.setDateStart(rs.getString("date_start"));
        mechanic.setDateEnd(rs.getString("date_end"));
        return mechanic;
    }
}
