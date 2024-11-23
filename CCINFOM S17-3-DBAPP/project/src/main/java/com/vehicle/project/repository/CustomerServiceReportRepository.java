package com.vehicle.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CustomerServiceReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getCustomerServiceReport(int year, int month) {
        String sql = "SELECT c.customer_id, " +
                     "       c.first_name, " +
                     "       c.last_name, " +
                     "       COUNT(sh.vehicle_id) AS total_vehicles_serviced, " +
                     "       SUM(sh.total_cost) AS total_service_cost " +
                     "FROM Customer_table c " +
                     "JOIN Service_history_table sh ON c.customer_id = sh.customer_id " +
                     "WHERE YEAR(sh.date_start) = ? AND MONTH(sh.date_start) = ? " +
                     "GROUP BY c.customer_id, c.first_name, c.last_name " +
                     "ORDER BY c.customer_id";

        return jdbcTemplate.queryForList(sql, year, month);
    }
}
