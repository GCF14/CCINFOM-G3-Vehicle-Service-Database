package com.vehicle.project.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vehicle.project.model.CustomerAndVehicle;
import com.vehicle.project.model.Mechanic;
import com.vehicle.project.model.Stock;
import com.vehicle.project.model.WorkingMechanic;
import com.vehicle.project.model.Customer;
import com.vehicle.project.model.Service;
import com.vehicle.project.model.ServiceHistoryView;
import com.vehicle.project.model.ServiceWithUpgrades;
import com.vehicle.project.mapper.CustomerAndVehicleRowMapper;
import com.vehicle.project.mapper.CustomersRowMapper;
import com.vehicle.project.mapper.ServiceHistoryViewRowMapper;
import com.vehicle.project.mapper.ServicesWithUpgradesRowMapper;
import com.vehicle.project.mapper.ViewMechanicRowMapper;
import com.vehicle.project.mapper.ViewServicesRowMapper;
import com.vehicle.project.mapper.ViewStockRowMapper;
import com.vehicle.project.mapper.WorkingMechanicsRowMapper;

@Repository
public class ViewRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ServiceHistoryView> findServiceHistoryViews() {
        String sql = "SELECT * FROM service_history_view ORDER BY total_cost DESC";
        return jdbcTemplate.query(sql, new ServiceHistoryViewRowMapper());
    }

    public List<CustomerAndVehicle> getCustomerAndVehicleView() {
        String sql = "SELECT * FROM customer_and_vehicle";
        return jdbcTemplate.query(sql, new CustomerAndVehicleRowMapper());
    }

    public List<Mechanic> getMechanicsView() {
        String sql = "SELECT * FROM mechanics";
        return jdbcTemplate.query(sql, new ViewMechanicRowMapper());
    }

    public List<Stock> getStocksView() {
        String sql = "SELECT * FROM stocks";
        return jdbcTemplate.query(sql, new ViewStockRowMapper());
    }

    public List<Service> getServicesView() {
        String sql = "SELECT * FROM services";
        return jdbcTemplate.query(sql, new ViewServicesRowMapper());
    }

    public List<ServiceWithUpgrades> getServicesWithUpgradesView() {
        String sql = "SELECT * FROM services_with_upgrades";
        return jdbcTemplate.query(sql, new ServicesWithUpgradesRowMapper());
    }

    public List<Customer> getCustomersView() {
        String sql = "SELECT * FROM customers";
        return jdbcTemplate.query(sql, new CustomersRowMapper());
    }

    public List<WorkingMechanic> getWorkingMechanicsView() {
        String sql = "SELECT * FROM working_mechanics";
        return jdbcTemplate.query(sql, new WorkingMechanicsRowMapper());
    }
    
}
