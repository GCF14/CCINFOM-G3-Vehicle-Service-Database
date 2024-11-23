package com.vehicle.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class StockUsageReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getStockUsageReport(int year, int month) {
        String sql = "SELECT st.name AS stock_name, st.price AS stock_price, su.quantity, " +
                     "(st.price * su.quantity) AS total_cost, s.service_type, sh.date_start " +
                     "FROM Stock_usage_table su " +
                     "JOIN Stock_table st ON su.stock_id = st.stock_id " +
                     "JOIN Service_history_table sh ON su.service_history_id = sh.service_history_id " +
                     "JOIN Service_table s ON sh.service_id = s.service_id " +
                     "WHERE YEAR(sh.date_start) = ? AND MONTH(sh.date_start) = ? " +
                     "ORDER BY sh.date_start ASC, su.stock_usage_id";
        
        return jdbcTemplate.queryForList(sql, year, month);
    }
}
