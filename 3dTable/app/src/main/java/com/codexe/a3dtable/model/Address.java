package com.codexe.a3dtable.model;

public class Address {

    private String city, details;

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetails() {
        return details;
    }

    public Address(String city, String details) {
        this.city = city;
        this.details = details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
