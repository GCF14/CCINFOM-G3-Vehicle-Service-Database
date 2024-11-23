package com.vehicle.project.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class MechanicPerformanceReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findMechanicPerformanceByYearMonth(String yearMonth) {
        String sql = "SELECT m.mechanic_id, m.mechanic_last_name, m.mechanic_first_name, COUNT(sh.vehicle_id) AS total_cars_serviced, SUM(sh.total_cost) AS total_service_cost " +
                     "FROM Mechanic_table m LEFT JOIN Service_history_table sh ON m.mechanic_id = sh.mechanic_id " +
                     "WHERE DATE_FORMAT(sh.date_start, '%Y-%m') = ? " +
                     "GROUP BY m.mechanic_id, m.mechanic_last_name, m.mechanic_first_name " +
                     "ORDER BY m.mechanic_id";

        return jdbcTemplate.queryForList(sql, yearMonth);
    }
}
