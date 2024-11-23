package com.vehicle.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Custom exception class for error handling (you can customize this)
class ErrorException extends RuntimeException {
    public ErrorException(String message) {
        super(message);
    }
}

@RestController
@RequestMapping("/api")
public class DeleteController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(DeleteController.class);  // Logger initialization

    @DeleteMapping("/delete-record")
    public ResponseEntity<String> deleteRecord(@RequestParam String tableName, @RequestParam int id) {
        
        
        String query;
        String sql;

        try {
            switch (tableName) {
                case "Service_history_table":
                    query = "DELETE FROM Service_history_table WHERE service_history_id = ?";
                    break;
                case "Customer_table":
                    query = "DELETE FROM Customer_table WHERE customer_id = ?";
                    break;
                case "Vehicle_table":
                    query = "DELETE FROM Vehicle_table WHERE vehicle_id = ?";
                    break;
                case "Service_table":
                    query = "DELETE FROM Service_table WHERE service_id = ?";
                    break;
                case "Mechanic_table":
                    query = "DELETE FROM Mechanic_table WHERE mechanic_id = ?";
                    break;
                case "Stock_table":
                    query = "DELETE FROM Stock_table WHERE stock_id = ?";
                    break;
                case "Stock_usage_table":

                    
                    sql = "SELECT service_history_id FROM Stock_usage_table WHERE stock_usage_id = ?";
                    Integer history_id = jdbcTemplate.queryForObject(sql, Integer.class, id);

                    
                    sql = "SELECT service_id FROM Service_history_table WHERE service_history_id = ?";
                    Integer serviceID = jdbcTemplate.queryForObject(sql, Integer.class, history_id);

                    
                    sql = "SELECT base_cost FROM Service_table WHERE service_id = ?";
                    Float baseCost = jdbcTemplate.queryForObject(sql, Float.class, serviceID);

                    
                    sql = "UPDATE Service_history_table SET total_cost = ? WHERE service_history_id = ?";
                    jdbcTemplate.update(sql, baseCost, history_id);

                    
                    query = "DELETE FROM Stock_usage_table WHERE stock_usage_id = ?";

                    int rowsAffected = jdbcTemplate.update(query, id); 

                    if (rowsAffected > 0) {
                        return ResponseEntity.ok("Record deleted successfully.");
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found.");
                    }
    
                default:
                    throw new ErrorException("Invalid table name: " + tableName);
            }

        } catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new ErrorException("Unable to delete record because another record depends on it");
        } catch (Exception e){
           
            logger.error("An unexpected error occurred during deletion: ", e);  // This will log the exception details
            throw new ErrorException("An unexpected error occurred during deletion");
            
        }
        

        int rowsAffected = jdbcTemplate.update(query, id);

        if (rowsAffected > 0) {
            return ResponseEntity.ok("Record deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found.");
        }
    }
}