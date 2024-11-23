package com.vehicle.project.model;

public class StockUsage {

    private int id;
    private int quantity;
    private String serviceType;
    private String stock_item;
    private int service_history_id;


    public int getStockUsageId() {
        return id;
    }
    public void setStockUsageId(int id) {
        this.id = id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getServiceType() {
        return serviceType;
    }
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    public int getService_history_id() {
        return service_history_id;
    }
    public void setService_history_id(int service_history_id) {
        this.service_history_id = service_history_id;
    }
    public String getStock_item() {
        return stock_item;
    }
    public void setStock_item(String stock_item) {
        this.stock_item = stock_item;
    }

    

    

    
    
}
