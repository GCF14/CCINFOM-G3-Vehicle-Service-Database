package com.vehicle.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MechanicRankingRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getMechanicPerformanceReport(int year, int month) {
        String sql = "SELECT CONCAT(m.mechanic_first_name, ' ', m.mechanic_last_name) AS mechanic_name, " +
                     "COUNT(m.mechanic_id) AS total_services, AVG(sh.service_rating) AS average_rating " +
                     "FROM Mechanic_table m " +
                     "JOIN Service_history_table sh ON m.mechanic_id = sh.mechanic_id " +
                     "WHERE YEAR(sh.date_start) = ? AND MONTH(sh.date_start) = ? " +
                     "GROUP BY m.mechanic_id, m.mechanic_first_name, m.mechanic_last_name " +
                     "HAVING total_services > 0";

        return jdbcTemplate.queryForList(sql, year, month);
    }
}
