package com.vehicle.project.model;

public class Stock {

    private int id;
    private String name;
    private float price;
    private String manufacturingDate;
    private String warranty;

    public int getStockId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public float getPrice() {
        return price;
    }
    public String getManufacturingDate() {
        return manufacturingDate;
    }
    public String getWarranty() {
        return warranty;
    }
    public void setStockId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public void setManufacturingDate(String manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }
    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }


}
