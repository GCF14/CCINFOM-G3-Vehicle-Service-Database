package com.vehicle.project.mapper;


import org.springframework.jdbc.core.RowMapper;
import com.vehicle.project.model.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewServicesRowMapper implements RowMapper<Service> {
    @Override
    public Service mapRow(ResultSet rs, int rowNum) throws SQLException {
        Service service = new Service();
        service.setServiceType(rs.getString("service_type"));
        service.setBaseCost(rs.getFloat("base_cost"));
        return service;
    }
}
