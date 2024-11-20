package com.vehicle.project.model;

public class ServiceWithUpgrades {

    private String serviceType;
    private String customerName;
    private String brand;
    private String model;
    private String mechanicName;
    private String dateStart;
    private String dateEnd;
    private String stockName;
    private int quantityBought;
    private float totalCost;
    private int serviceRating;


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
    public String getStockName() {
        return stockName;
    }
    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
    public int getQuantityBought() {
        return quantityBought;
    }
    public void setQuantityBought(int quantityBought) {
        this.quantityBought = quantityBought;
    }
    public float getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }
    public int getServiceRating() {
        return serviceRating;
    }
    public void setServiceRating(int serviceRating) {
        this.serviceRating = serviceRating;
    }

    
}