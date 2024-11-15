package com.vehicle.project.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service  // Mark this class as a Spring-managed service
public class DatabaseService {

    @Autowired
    private DataSource dataSource;  // Let Spring manage the DataSource

    // Method to insert data into the database
    public void insertCustomerData() {
        Connection connection = null;
        Statement command = null;
        try {
            connection = dataSource.getConnection();  // Get connection from Spring DataSource
            command = connection.createStatement();
            command.execute("INSERT INTO Customer_table(customer_id, first_name, last_name, contact_details) "
                            + "VALUES (6, 'Naruto', 'Uzumaki', '09123456789');");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (command != null) command.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
