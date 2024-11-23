package com.vehicle.project.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vehicle.project.model.ServiceHistoryDelete;

import org.springframework.jdbc.core.RowMapper;


public class ServiceHistoryDeleteRowMapper implements RowMapper<ServiceHistoryDelete> {
    @Override
    public ServiceHistoryDelete mapRow(ResultSet rs, int rowNum) throws SQLException {

        ServiceHistoryDelete view = new ServiceHistoryDelete();
        view.setService_history_id(rs.getInt("service_history_id"));
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
