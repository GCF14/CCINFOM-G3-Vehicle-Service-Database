package com.vehicle.project.model;

public class ServiceHistoryDelete {
    private int id;
    private String serviceType;
    private String customerName;
    private String brand;
    private String model;
    private String mechanicName;
    private String dateStart;
    private String dateEnd;
    private double totalCost;
    private String serviceRating;

    
    
    public String getServiceType() {
        return serviceType;
    }
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getMechanicName() {
        return mechanicName;
    }
    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }
    public String getDateStart() {
        return dateStart;
    }
    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }
    public String getDateEnd() {
        return dateEnd;
    }
    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
    public double getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    public String getServiceRating() {
        return serviceRating;
    }
    public void setServiceRating(String serviceRating) {
        this.serviceRating = serviceRating;
    }
    public int getService_history_id() {
        return id;
    }
    public void setService_history_id(int id) {
        this.id = id;
    }
}
