package com.vehicle.project.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vehicle.project.model.Customer;
import org.springframework.jdbc.core.RowMapper;

public class CustomerDeleteRowMapper  implements RowMapper<Customer>{
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomer_id(rs.getInt("customer_id"));
        customer.setLastName(rs.getString("last_name"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setContactDetails(rs.getString("contact_details"));
        return customer;
    }
}
