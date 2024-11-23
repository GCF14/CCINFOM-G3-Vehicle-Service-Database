package com.vehicle.project.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.vehicle.project.exception.ErrorException;




import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class RecordController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-records")
    public List<Map<String, Object>> getRecords(@RequestParam String tableName) {
        String query;

        switch (tableName) {
            case "Service_history_table":
                query = "SELECT service_history_id AS id, CONCAT(service_type, ' by ', mechanic_name, ' (', brand, ' ', model, ')') AS details  FROM service_history_delete";
                break;
            case "Customer_table":
                query = "SELECT customer_id AS id, CONCAT(last_name, ', ', first_name, ' (', contact_details, ')') AS details FROM Customer_table";
                break;
            case "Vehicle_table":
                query = "SELECT vehicle_id AS id, CONCAT(brand, ' ', model) AS details FROM Vehicle_table";
                break;
            case "Service_table":
                query = "SELECT service_id AS id, CONCAT(service_type, ' - $', base_cost) AS details FROM Service_table";
                break;
            case "Mechanic_table":
                query = "SELECT mechanic_id AS id, CONCAT(mechanic_first_name, ' ', mechanic_last_name, ' ', hire_date, ' ', end_date)" + 
                " AS details FROM Mechanic_table";
                break;
            case "Stock_table":
                query = "SELECT stock_id AS id, CONCAT(name, ' ($', price, ')') AS details FROM Stock_table";
                break;
            case "Stock_usage_table":
                query = "SELECT stock_usage_id AS id, CONCAT(quantity, ' x ', name, ' , ', service_type) AS details FROM stock_use";
                break;
            default:
                throw new ErrorException("Invalid table name: " + tableName);
        }

        return jdbcTemplate.queryForList(query);
    }
}
