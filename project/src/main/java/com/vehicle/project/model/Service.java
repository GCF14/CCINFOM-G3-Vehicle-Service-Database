package com.vehicle.project.model;


public class Service {
    private int serviceId;
    private String serviceType;
    private float baseCost;

    public int getServiceId(){
        return serviceId;
    }

    public String getServiceType(){
        return serviceType;
    }

    public float getBaseCost(){
        return baseCost;
    }


    public void setServiceId(int serviceId){
        this.serviceId = serviceId;
    }

    public void setServiceType(String serviceType){
        this.serviceType = serviceType;
    }

    public void setBaseCost(float baseCost){
        this.baseCost = baseCost;
    }

    @Override
    public String toString() {
        return "Service[id = " + serviceId + "type = " + serviceType + "base cost = " + baseCost + "]";
    }
}
