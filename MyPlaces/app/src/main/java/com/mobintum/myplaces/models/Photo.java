package com.mobintum.myplaces.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Ricardo on 05/05/17.
 * www.mobintum.com
 */

public class Photo {
    @Expose
    private String id;
    @Expose
    private String prefix;
    @Expose
    private String suffix;
    @Expose
    private int width;
    @Expose
    private int height;

    public Photo(String id, String prefix, String suffix, int width, int height) {
        this.id = id;
        this.prefix = prefix;
        this.suffix = suffix;
        this.width = width;
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
