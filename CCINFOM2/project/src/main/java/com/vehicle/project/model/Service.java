package com.vehicle.project.model;


public class Service {
    
    private int id;
    private String serviceType;
    private float baseCost;

    public int getServiceId(){
        return id;
    }

    public String getServiceType(){
        return serviceType;
    }

    public float getBaseCost(){
        return baseCost;
    }


    public void setServiceId(int id){
        this.id = id;
    }

    public void setServiceType(String serviceType){
        this.serviceType = serviceType;
    }

    public void setBaseCost(float baseCost){
        this.baseCost = baseCost;
    }

    @Override
    public String toString() {
        return "Service[id = " + id + "type = " + serviceType + "base cost = " + baseCost + "]";
    }
}
