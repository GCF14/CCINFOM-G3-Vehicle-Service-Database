package com.vehicle.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.vehicle.project.model.Service;
import com.vehicle.project.mapper.ServiceRowMapper;

@Repository
public class ServiceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<Service> findAll() {
        String sql = "SELECT * FROM Service_table"; 
        return jdbcTemplate.query(sql, new ServiceRowMapper());
    }

  
}
