package com.mobintum.myplaces.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ricardo on 05/05/17.
 * www.mobintum.com
 */

public class Venue {

    @SerializedName("id")
    @Expose
    private String venueId;
    @Expose
    private String name;
    @Expose
    private Location location;

    public Venue(String venueId, String name, Location location) {
        this.venueId = venueId;
        this.name = name;
        this.location = location;
    }

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
