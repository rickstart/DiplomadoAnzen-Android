package com.mobintum.feedplaces.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ricardo on 19/05/17.
 * www.mobintum.com
 */

public class Place implements Serializable {

    private String name;
    private String description;
    private Address  address;
    private List<Picture> pictures;

    public Place(String name, String description, Address address, List<Picture> pictures) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.pictures = pictures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
