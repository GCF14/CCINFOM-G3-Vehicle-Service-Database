package com.vehicle.project.model;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String contactDetails;

    public int getCustomer_id() {
        return id;
    }
    public void setCustomer_id(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getContactDetails() {
        return contactDetails;
    }
    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    
}
