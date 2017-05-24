package com.mobintum.feedplaces.models;

/**
 * Created by Ricardo on 19/05/17.
 * www.mobintum.com
 */

public class Address {

    private String country;
    private String state;
    private String addressLine;
    private Double lat;
    private Double lng;

    public Address(String country, String state, String addressLine, Double lat, Double lng) {
        this.country = country;
        this.state = state;
        this.addressLine = addressLine;
        this.lat = lat;
        this.lng = lng;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
