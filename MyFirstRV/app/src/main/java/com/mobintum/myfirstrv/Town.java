package com.mobintum.myfirstrv;

/**
 * Created by Ricardo on 03/05/17.
 * www.mobintum.com
 */

public class Town {

    private String name;
    private String state;
    private float rating;
    private String pathImage;

    public Town(String name, String state, float rating, String pathImage) {
        this.name = name;
        this.state = state;
        this.rating = rating;
        this.pathImage = pathImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }
}
