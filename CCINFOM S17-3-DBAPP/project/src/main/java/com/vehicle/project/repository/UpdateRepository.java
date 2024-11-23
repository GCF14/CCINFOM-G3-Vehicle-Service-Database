package com.vehicle.project.repository;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UpdateRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void updateCustomer(int id, String firstName, String lastName, String contactInfo) {
        String sql = "UPDATE Customer_table SET first_name = ?, last_name = ?, contact_details = ? WHERE customer_id = ?";
        jdbcTemplate.update(sql, firstName, lastName, contactInfo, id);
    }

    public void updateServiceHistory(int id, String currentDate, String endDate, int rating) {
        String sql = "UPDATE Service_history_table SET date_start = ?, date_end = ?, service_rating = ? WHERE service_history_id = ?";
        jdbcTemplate.update(sql, currentDate, endDate, rating, id);
    }

    public void updateServiceHistory(int id, String currentDate) {
        String sql = "UPDATE Service_history_table SET date_start = ? WHERE service_history_id = ?";
        jdbcTemplate.update(sql, currentDate, id);
    }

    public void updateServiceHistory(int id, String endDate, int rating) {
        String sql = "UPDATE Service_history_table SET date_end = ?, service_rating = ? WHERE service_history_id = ?";
        jdbcTemplate.update(sql, endDate, rating, id);
    }

    public void updateMechanic(int id, String firstName, String hireDate, String endDate) {
        String sql = "UPDATE Mechanic_table SET mechanic_first_name = ?, hire_date = ?, end_date = ? WHERE mechanic_id = ?";
        jdbcTemplate.update(sql, firstName, hireDate, endDate, id);
    }

    public void updateMechanic2(int id, String lastName, String hireDate, String endDate) {
        String sql = "UPDATE Mechanic_table SET mechanic_last_name = ?, hire_date = ?, end_date = ? WHERE mechanic_id = ?";
        jdbcTemplate.update(sql, lastName, hireDate, endDate, id);
    }

    public void updateMechanic3(int id, String firstName, String lastName, String hireDate, String endDate) {
        String sql = "UPDATE Mechanic_table SET mechanic_first_name = ?, mechanic_last_name = ?, hire_date = ?, end_date = ? WHERE mechanic_id = ?";
        jdbcTemplate.update(sql, firstName, lastName, hireDate, endDate, id);
    }

    public void updateVehicle(int id, String brand, String model, String year){
        String sql = "UPDATE Vehicle_table SET brand = ?, model = ?, year_made = ? WHERE vehicle_id = ?";
        jdbcTemplate.update(sql, brand, model, year, id);
    }

    public void updateVehicle2(int id, String brand, String year){
        String sql = "UPDATE Vehicle_table SET brand = ?, year_made = ? WHERE vehicle_id = ?";
        jdbcTemplate.update(sql, brand, year, id);
    }

    public void updateVehicle3(int id, String model, String year){
        String sql = "UPDATE Vehicle_table SET model = ?, year_made = ? WHERE vehicle_id = ?";
        jdbcTemplate.update(sql, model, year, id);
    }

    public void updateStock(int id, String name, float newPrice, String manufacturedDate, String warranty) {
        // Update the stock price in Stock_table
        String updateStockPriceSql = "UPDATE Stock_table SET name = ?, price = ?, manufacturing_date = ?, warranty = ? WHERE stock_id = ?";
        jdbcTemplate.update(updateStockPriceSql, name, newPrice, manufacturedDate, warranty, id);

        String sql = "SELECT COUNT(*) FROM Stock_usage_table WHERE stock_id = ?";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, id);

        if(result > 0) {

            // Fetch all service_history_ids and quantities from Stock_usage_table for the updated stock
            String fetchUsageSql = "SELECT service_history_id, quantity FROM Stock_usage_table WHERE stock_id = ?";
            List<Map<String, Object>> usageRecords = jdbcTemplate.queryForList(fetchUsageSql, id);

            // Iterate through each record and calculate the new total_cost
            for (Map<String, Object> record : usageRecords) {
                Integer serviceHistoryId = (Integer) record.get("service_history_id");
                Integer quantity = (Integer) record.get("quantity");

                if (serviceHistoryId != null && quantity != null) {
                    // Fetch the corresponding service_id from Service_history_table
                    String fetchServiceIdSql = "SELECT service_id FROM Service_history_table WHERE service_history_id = ?";
                    Integer serviceId = jdbcTemplate.queryForObject(fetchServiceIdSql, Integer.class, serviceHistoryId);

                    // Fetch the base_cost for the service_id from Service_table
                    String fetchBaseCostSql = "SELECT base_cost FROM Service_table WHERE service_id = ?";
                    Float baseCost = jdbcTemplate.queryForObject(fetchBaseCostSql, Float.class, serviceId);

                    if (baseCost == null) {
                        baseCost = 0f; 
                    }

                    // Calculate the new total_cost
                    Float totalCost = baseCost + (newPrice * quantity);

                    // Update the total_cost in Service_history_table
                    String updateTotalCostSql = "UPDATE Service_history_table SET total_cost = ? WHERE service_history_id = ?";
                    jdbcTemplate.update(updateTotalCostSql, totalCost, serviceHistoryId);
                }
            }

        }
        
    }

    public void updateStock2(int id, String name, String manufacturedDate, String warranty){
        String sql = "UPDATE Stock_table SET name = ?, manufacturing_date = ?, warranty = ? WHERE stock_id = ?";
        jdbcTemplate.update(sql, name, manufacturedDate, warranty, id);
    }

    public void updateStock3(int id, float newPrice, String manufacturedDate, String warranty){
     
        // Update the stock price in Stock_table
        String updateStockPriceSql = "UPDATE Stock_table SET price = ?, manufacturing_date = ?, warranty = ? WHERE stock_id = ?";
        jdbcTemplate.update(updateStockPriceSql, newPrice, manufacturedDate, warranty, id);

        String sql = "SELECT COUNT(*) FROM Stock_usage_table WHERE stock_id = ?";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, id);

        if(result > 0) {

            // Fetch all service_history_ids and quantities from Stock_usage_table for the updated stock
            String fetchUsageSql = "SELECT service_history_id, quantity FROM Stock_usage_table WHERE stock_id = ?";
            List<Map<String, Object>> usageRecords = jdbcTemplate.queryForList(fetchUsageSql, id);

            // Iterate through each record and calculate the new total_cost
            for (Map<String, Object> record : usageRecords) {
                Integer serviceHistoryId = (Integer) record.get("service_history_id");
                Integer quantity = (Integer) record.get("quantity");

                if (serviceHistoryId != null && quantity != null) {
                    // Fetch the corresponding service_id from Service_history_table
                    String fetchServiceIdSql = "SELECT service_id FROM Service_history_table WHERE service_history_id = ?";
                    Integer serviceId = jdbcTemplate.queryForObject(fetchServiceIdSql, Integer.class, serviceHistoryId);

                    // Fetch the base_cost for the service_id from Service_table
                    String fetchBaseCostSql = "SELECT base_cost FROM Service_table WHERE service_id = ?";
                    Float baseCost = jdbcTemplate.queryForObject(fetchBaseCostSql, Float.class, serviceId);

                    if (baseCost == null) {
                        baseCost = 0f; 
                    }

                    // Calculate the new total_cost
                    Float totalCost = baseCost + (newPrice * quantity);

                    // Update the total_cost in Service_history_table
                    String updateTotalCostSql = "UPDATE Service_history_table SET total_cost = ? WHERE service_history_id = ?";
                    jdbcTemplate.update(updateTotalCostSql, totalCost, serviceHistoryId);
                }
            }

        }
    }




    public void updateService(int id, float newPrice) {
        // Update the service name and price in Service_table
        String updateServicePrice = "UPDATE Service_table SET base_cost = ? WHERE service_id = ?";
        jdbcTemplate.update(updateServicePrice, newPrice, id);
    
        // Check if there are records in Stock_usage_table for the given service_id
        String countUsageSql = "SELECT COUNT(sh.service_history_id) " +
                "FROM Service_history_table sh " +
                "JOIN Stock_usage_table su " +
                "ON sh.service_history_id = su.service_history_id " +
                "WHERE sh.service_id = ?";
        Integer usageCount = jdbcTemplate.queryForObject(countUsageSql, Integer.class, id);
       
    
        // Fetch all service_history_ids related to this service_id
        String fetchHistorySql = "SELECT service_history_id FROM Service_history_table WHERE service_id = ?";
        List<Integer> historyIds = jdbcTemplate.queryForList(fetchHistorySql, Integer.class, id);
        
    
        if (usageCount > 0) {
            // Fetch all usage records for the updated service
            String fetchUsageSql = "SELECT sh.service_history_id, su.quantity " +
                    "FROM Service_history_table sh " +
                    "JOIN Stock_usage_table su " +
                    "ON sh.service_history_id = su.service_history_id " +
                    "WHERE sh.service_id = ?";
            List<Map<String, Object>> usageRecords = jdbcTemplate.queryForList(fetchUsageSql, id);
            
    
            // Fetch the base cost for the service
            String fetchBaseCostSql = "SELECT base_cost FROM Service_table WHERE service_id = ?";
            Float baseCost = jdbcTemplate.queryForObject(fetchBaseCostSql, Float.class, id);
            baseCost = (baseCost != null) ? baseCost : 0f;
           
    
            // Iterate over usage records to update total_cost
            for (Map<String, Object> record : usageRecords) {
                Integer serviceHistoryId = (Integer) record.get("service_history_id");
                Integer quantity = (Integer) record.get("quantity");
    
                if (serviceHistoryId != null && quantity != null) {
                    // Fetch the stock_id for this service_history_id
                    String fetchStockIdSql = "SELECT su.stock_id " +
                            "FROM Service_history_table sh " +
                            "JOIN Stock_usage_table su " +
                            "ON sh.service_history_id = su.service_history_id " +
                            "WHERE sh.service_history_id = ? AND sh.service_id = ?";
                   
                    Integer stockId = jdbcTemplate.queryForObject(fetchStockIdSql, Integer.class, serviceHistoryId, id);
                    
    
                    // Fetch the price of the stock
                    String fetchStockPriceSql = "SELECT price FROM Stock_table WHERE stock_id = ?";
                    
                    Float stockPrice = jdbcTemplate.queryForObject(fetchStockPriceSql, Float.class, stockId);
                  
    
                    // Calculate total cost with quantity
                    Float totalCost = baseCost + (stockPrice * quantity);
                 
    
                    // Update the total_cost in Service_history_table
                    String updateTotalCostSql = "UPDATE Service_history_table SET total_cost = ? WHERE service_history_id = ?";
                    jdbcTemplate.update(updateTotalCostSql, totalCost, serviceHistoryId);
                   
                }
            }
    
            // Handle service_history_ids that do not exist in usage records
            for (Integer historyId : historyIds) {
                boolean existsInUsage = usageRecords.stream()
                        .anyMatch(record -> record.get("service_history_id").equals(historyId));
    
                if (!existsInUsage) {
                    String updateBaseCostSql = "UPDATE Service_history_table SET total_cost = ? WHERE service_history_id = ?";
                    jdbcTemplate.update(updateBaseCostSql, baseCost, historyId);
                    System.out.println("Executed updateBaseCostSql for historyId: " + historyId);
                }
            }
        } else {
            // If no usage records exist, update all related service_history_ids with the new price
            for (Integer historyId : historyIds) {
                String updatePriceSql = "UPDATE Service_history_table SET total_cost = ? WHERE service_history_id = ?";
                jdbcTemplate.update(updatePriceSql, newPrice, historyId);
                System.out.println("Executed updatePriceSql for historyId: " + historyId);
            }
        }
    }

    public void updateService2(int id, String name) {
        // Update the service name and price in Service_table
        String updateServicePrice = "UPDATE Service_table SET service_type = ? WHERE service_id = ?";
        jdbcTemplate.update(updateServicePrice, name, id);
    }

    public void updateService3(int id, String name, float newPrice) {
        // Update the service name and price in Service_table
        String updateServicePrice = "UPDATE Service_table SET service_type = ?, base_cost = ? WHERE service_id = ?";
        jdbcTemplate.update(updateServicePrice, name, newPrice, id);
    
        // Check if there are records in Stock_usage_table for the given service_id
        String countUsageSql = "SELECT COUNT(sh.service_history_id) " +
                "FROM Service_history_table sh " +
                "JOIN Stock_usage_table su " +
                "ON sh.service_history_id = su.service_history_id " +
                "WHERE sh.service_id = ?";
        Integer usageCount = jdbcTemplate.queryForObject(countUsageSql, Integer.class, id);
       
    
        // Fetch all service_history_ids related to this service_id
        String fetchHistorySql = "SELECT service_history_id FROM Service_history_table WHERE service_id = ?";
        List<Integer> historyIds = jdbcTemplate.queryForList(fetchHistorySql, Integer.class, id);
        
    
        if (usageCount > 0) {
            // Fetch all usage records for the updated service
            String fetchUsageSql = "SELECT sh.service_history_id, su.quantity " +
                    "FROM Service_history_table sh " +
                    "JOIN Stock_usage_table su " +
                    "ON sh.service_history_id = su.service_history_id " +
                    "WHERE sh.service_id = ?";
            List<Map<String, Object>> usageRecords = jdbcTemplate.queryForList(fetchUsageSql, id);
            
    
            // Fetch the base cost for the service
            String fetchBaseCostSql = "SELECT base_cost FROM Service_table WHERE service_id = ?";
            Float baseCost = jdbcTemplate.queryForObject(fetchBaseCostSql, Float.class, id);
            baseCost = (baseCost != null) ? baseCost : 0f;
           
    
            // Iterate over usage records to update total_cost
            for (Map<String, Object> record : usageRecords) {
                Integer serviceHistoryId = (Integer) record.get("service_history_id");
                Integer quantity = (Integer) record.get("quantity");
    
                if (serviceHistoryId != null && quantity != null) {
                    // Fetch the stock_id for this service_history_id
                    String fetchStockIdSql = "SELECT su.stock_id " +
                            "FROM Service_history_table sh " +
                            "JOIN Stock_usage_table su " +
                            "ON sh.service_history_id = su.service_history_id " +
                            "WHERE sh.service_history_id = ? AND sh.service_id = ?";
                   
                    Integer stockId = jdbcTemplate.queryForObject(fetchStockIdSql, Integer.class, serviceHistoryId, id);
                    
    
                    // Fetch the price of the stock
                    String fetchStockPriceSql = "SELECT price FROM Stock_table WHERE stock_id = ?";
                    
                    Float stockPrice = jdbcTemplate.queryForObject(fetchStockPriceSql, Float.class, stockId);
                  
    
                    // Calculate total cost with quantity
                    Float totalCost = baseCost + (stockPrice * quantity);
                 
    
                    // Update the total_cost in Service_history_table
                    String updateTotalCostSql = "UPDATE Service_history_table SET total_cost = ? WHERE service_history_id = ?";
                    jdbcTemplate.update(updateTotalCostSql, totalCost, serviceHistoryId);
                   
                }
            }
    
            // Handle service_history_ids that do not exist in usage records
            for (Integer historyId : historyIds) {
                boolean existsInUsage = usageRecords.stream()
                        .anyMatch(record -> record.get("service_history_id").equals(historyId));
    
                if (!existsInUsage) {
                    String updateBaseCostSql = "UPDATE Service_history_table SET total_cost = ? WHERE service_history_id = ?";
                    jdbcTemplate.update(updateBaseCostSql, baseCost, historyId);
                    System.out.println("Executed updateBaseCostSql for historyId: " + historyId);
                }
            }
        } else {
            // If no usage records exist, update all related service_history_ids with the new price
            for (Integer historyId : historyIds) {
                String updatePriceSql = "UPDATE Service_history_table SET total_cost = ? WHERE service_history_id = ?";
                jdbcTemplate.update(updatePriceSql, newPrice, historyId);
                System.out.println("Executed updatePriceSql for historyId: " + historyId);
            }
        }
    }


    public void updateStockUsage(int id, int quantity) {
       
        String sql = "UPDATE Stock_usage_table SET quantity = ? WHERE stock_usage_id = ?";
        jdbcTemplate.update(sql, quantity, id);

        sql = "SELECT stock_id FROM Stock_usage_table WHERE stock_usage_id = ?";
        Integer stockID = jdbcTemplate.queryForObject(sql, Integer.class, id);

        sql = "SELECT price FROM Stock_table WHERE stock_id = ?";
        Float price = jdbcTemplate.queryForObject(sql, Float.class, stockID);

        // Fetch all usage records for the updated service
        sql = "SELECT service_history_id FROM Stock_usage_table WHERE stock_usage_id = ?";
        Integer historyID = jdbcTemplate.queryForObject(sql, Integer.class, id);

        sql = "SELECT service_id FROM Service_history_table WHERE service_history_id = ?";
        Integer serviceID =  jdbcTemplate.queryForObject(sql, Integer.class, historyID);

        sql = "SELECT base_cost FROM Service_table WHERE service_id = ?";
        Float baseCost = jdbcTemplate.queryForObject(sql, Float.class, serviceID);

        sql = "UPDATE Service_history_table SET total_cost = ? WHERE service_history_id = ?";
        jdbcTemplate.update(sql, baseCost + (price * quantity), historyID);

       
        
    }
    

    
}
