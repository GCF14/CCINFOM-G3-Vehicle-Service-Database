package com.vehicle.project.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.vehicle.project.model.Submit;
import com.vehicle.project.exception.ErrorException;

@Repository
public class SubmitRepository {

    private final JdbcTemplate jdbcTemplate;

   
    public SubmitRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    
    public int saveHistory(Submit submit) {

        Integer result;
        Integer customerId;
        Integer vehicleId;
        String sqlChecker;

        // Check if the vehicle and customer already exist in the service history table on the same date
        sqlChecker = "SELECT COUNT(*) FROM Service_history_table s JOIN Vehicle_table v ON s.vehicle_id = v.vehicle_id "
        + "JOIN Customer_table c ON s.customer_id = c.customer_id WHERE c.first_name = ? AND c.last_name = ? AND v.brand = ? "
        + "AND v.model = ? AND s.date_start = ?";
        result = jdbcTemplate.queryForObject(sqlChecker, Integer.class, submit.getFirstName(), submit.getLastName(), submit.getBrand(),
        submit.getModel(), submit.getDate());

        

        // If a record exists then it probably has different mechanics which we will not allow
        if (result > 0) {
            throw new ErrorException("A service with the same customer, vehicle, and date already exists. " +
            "Furthermore, another mechanic cannot be assigned to the same vehicle on the same day.");
        }

        //checking if current mechanic in the forms already is booked for the day
        sqlChecker = "SELECT COUNT(*) FROM Service_history_table WHERE mechanic_id = ? AND date_start = ?";
        Integer mechanicCount = jdbcTemplate.queryForObject(sqlChecker, Integer.class, submit.getMechanic(), submit.getDate());
        
        if (mechanicCount > 0) {
            throw new ErrorException("Mechanic already has a service scheduled on this date.");
        }

        

        //This is for when the customer and vehicle do not exist yet in the table
        String sqlChecker2 = "SELECT COUNT(*) FROM Customer_table c JOIN Vehicle_table v ON c.customer_id = v.customer_id" +
        " WHERE c.first_name = ? AND c.last_name = ? AND v.brand = ? AND v.model = ?;";
        result = jdbcTemplate.queryForObject(sqlChecker2, Integer.class, submit.getFirstName(), 
        submit.getLastName(), submit.getBrand(), submit.getModel());

        if(result == 0) {

            //This will check if the customer already existed before
            sqlChecker2 = "SELECT COUNT(*) FROM Customer_table WHERE first_name = ? AND last_name = ?";
            result = jdbcTemplate.queryForObject(sqlChecker2, Integer.class, submit.getFirstName(), submit.getLastName());

            //if customer does not yet exist
            if(result == 0) {
                String sql = "INSERT INTO Customer_table (first_name, last_name, contact_details) VALUES (?, ?, ?)";
                jdbcTemplate.update(sql, submit.getFirstName(), submit.getLastName(), submit.getPhone());

        
                String sql2 = "SELECT customer_id FROM Customer_table ORDER BY customer_id DESC LIMIT 1";
                customerId = jdbcTemplate.queryForObject(sql2, Integer.class); 
            } else {
                //this is for when the customer exists already
                String sql2 = "SELECT customer_id FROM Customer_table WHERE first_name = ? AND last_name = ?";
                customerId = jdbcTemplate.queryForObject(sql2, Integer.class, submit.getFirstName(), submit.getLastName()); 

                
                if (customerId == null) {
                    return -1;
                }

            }

            
            String sql3 = "INSERT INTO Vehicle_table (customer_id, brand, model, year_made) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql3, customerId, submit.getBrand(), submit.getModel(), submit.getYearMade());


            String sql4 = "SELECT vehicle_id FROM Vehicle_table ORDER BY vehicle_id DESC LIMIT 1";
            vehicleId = jdbcTemplate.queryForObject(sql4, Integer.class); 

            if (vehicleId == null) {
                return -1;
            }

            String sql5 = "INSERT INTO Service_history_table(service_id, customer_id, vehicle_id, mechanic_id, date_start, date_end,"
            + "total_cost, service_rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            String sql7 = "SELECT base_cost FROM Service_table WHERE service_id = ?";
            Integer baseCost = jdbcTemplate.queryForObject(sql7, Integer.class, submit.getService());

            if(submit.getStock() != 0) {
                String sql6 = "SELECT price FROM Stock_table WHERE stock_id = ?";
                Integer price = jdbcTemplate.queryForObject(sql6, Integer.class, submit.getStock());

                jdbcTemplate.update(sql5, submit.getService(), customerId, vehicleId, submit.getMechanic(), submit.getDate(), null, baseCost + (price * submit.getQuantity()), null);

                sql6 = "SELECT service_history_id FROM Service_history_table ORDER BY service_history_id DESC LIMIT 1";
                Integer service_history_id = jdbcTemplate.queryForObject(sql6, Integer.class);

                sql6 = "INSERT INTO Stock_usage_table (service_history_id, stock_id, quantity) VALUES (?, ?, ?)";
                return jdbcTemplate.update(sql6, service_history_id, submit.getStock(), submit.getQuantity());
                
            } else {
                return jdbcTemplate.update(sql5, submit.getService(), customerId, vehicleId, submit.getMechanic(), submit.getDate(), null, baseCost, null);
            }

        } else {

            //This is for when the customer and vehicle already exist in the table
            String sql8 = "SELECT customer_id FROM Customer_table WHERE first_name = ? AND last_name = ?";
            customerId = jdbcTemplate.queryForObject(sql8, Integer.class, submit.getFirstName(), submit.getLastName());

            sql8 = "SELECT vehicle_id FROM Vehicle_table WHERE brand = ? AND model = ?";
            vehicleId = jdbcTemplate.queryForObject(sql8, Integer.class, submit.getBrand(), submit.getModel());


            sql8 = "INSERT INTO Service_history_table(service_id, customer_id, vehicle_id, mechanic_id, date_start, date_end,"
            + "total_cost, service_rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            String sql7 = "SELECT base_cost FROM Service_table WHERE service_id = ?";
            Integer baseCost = jdbcTemplate.queryForObject(sql7, Integer.class, submit.getService());

            if(submit.getStock() != 0) {
                String sql6 = "SELECT price FROM Stock_table WHERE stock_id = ?";

                Integer price = jdbcTemplate.queryForObject(sql6, Integer.class, submit.getStock());
                jdbcTemplate.update(sql8, submit.getService(), customerId, vehicleId, submit.getMechanic(), submit.getDate(), null, baseCost + (price * submit.getQuantity()), null);

                sql8 = "SELECT service_history_id FROM Service_history_table ORDER BY service_history_id DESC LIMIT 1";
                Integer service_history_id = jdbcTemplate.queryForObject(sql8, Integer.class);

                sql6 = "INSERT INTO Stock_usage_table (service_history_id, stock_id, quantity) VALUES (?, ?, ?)";
                return jdbcTemplate.update(sql6, service_history_id, submit.getStock(), submit.getQuantity());
            } else {
                return jdbcTemplate.update(sql8, submit.getService(), customerId, vehicleId, submit.getMechanic(), submit.getDate(), null, baseCost, null);
            }












        }

        

        
    }
        
        
        
        
}
