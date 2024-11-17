package com.vehicle.project.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Service_table")
public class VehicleService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;

    private String serviceType;
    private BigDecimal baseCost;

    // Getters and Setters
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public BigDecimal getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(BigDecimal baseCost) {
        this.baseCost = baseCost;
    }
}