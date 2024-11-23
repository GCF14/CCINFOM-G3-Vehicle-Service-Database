package com.vehicle.project.mapper;

import com.vehicle.project.model.ServiceHistoryView;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceHistoryViewRowMapper implements RowMapper<ServiceHistoryView> {
    @Override
    public ServiceHistoryView mapRow(ResultSet rs, int rowNum) throws SQLException {
        ServiceHistoryView view = new ServiceHistoryView();
        
        view.setServiceType(rs.getString("service_type"));
        view.setCustomerName(rs.getString("customer_name"));
        view.setBrand(rs.getString("brand"));
        view.setModel(rs.getString("model"));
        view.setMechanicName(rs.getString("mechanic_name"));
        view.setDateStart(rs.getString("date_start"));
        view.setDateEnd(rs.getString("date_end"));
        view.setTotalCost(rs.getDouble("total_cost"));
        view.setServiceRating(rs.getString("service_rating"));
        
        return view;
    }
}