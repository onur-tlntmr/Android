package com.codexe.a3dtable.model;

import java.io.Serializable;

public class Address implements Serializable {

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

    public String getFullAddress(){

        StringBuilder sb = new StringBuilder();

        sb.append(details);
        sb.append('/');
        sb.append(city);

        return sb.toString();
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
