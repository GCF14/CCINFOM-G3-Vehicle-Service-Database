package com.vehicle.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @DeleteMapping("/delete-record")
    public ResponseEntity<String> deleteRecord(@RequestParam String tableName, @RequestParam int id) {
        
        String query;
        String sql;
        Integer count;

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
                sql = "SELECT COUNT(*) FROM Stock_usage_table su JOIN Stock_table st ON su.stock_id = st.stock_id WHERE su.stock_id = ?";
                count = jdbcTemplate.queryForObject(sql, Integer.class, id);

                if(count == 0){
                    query = "DELETE FROM Stock_table WHERE stock_id = ?";
                } else {
                    throw new ErrorException("Error Deleting. A record of this stock exists in Stock_usage_table.");
                }
                
                break;
            case "Stock_usage_table":
                query = "DELETE FROM Stock_usage_table WHERE stock_usage_id = ?";
                break;
            default:
                throw new ErrorException("Invalid table name: " + tableName);
        }

        int rowsAffected = jdbcTemplate.update(query, id);

        if (rowsAffected > 0) {
            return ResponseEntity.ok("Record deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found.");
        }
    }
}