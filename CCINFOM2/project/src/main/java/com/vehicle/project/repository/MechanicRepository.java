package com.vehicle.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.vehicle.project.model.Mechanic;
import com.vehicle.project.exception.ErrorException;
import com.vehicle.project.mapper.MechanicRowMapper;

@Repository
public class MechanicRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<Mechanic> findAll() {
        String sql = "SELECT * FROM Mechanic_table"; 
        return jdbcTemplate.query(sql, new MechanicRowMapper());
    }

    public int hireMechanic(Mechanic mechanic) {
        String sqlChecker = "SELECT COUNT(*) FROM Mechanic_table WHERE mechanic_first_name = ? AND mechanic_last_name = ?";
        int count = jdbcTemplate.queryForObject(sqlChecker, Integer.class, mechanic.getFirstName(), mechanic.getLastName());

        if(count == 0){
            String sql = "INSERT INTO Mechanic_table(mechanic_first_name, mechanic_last_name, hire_date, end_date) VALUES (?, ?, ?, ?)";
            return jdbcTemplate.update(sql, mechanic.getFirstName(), mechanic.getLastName(), mechanic.getHireDate(), mechanic.getEndDate());
        } else {
            throw new ErrorException("That mechanic has already been hired into the company");
        }
    }
}
