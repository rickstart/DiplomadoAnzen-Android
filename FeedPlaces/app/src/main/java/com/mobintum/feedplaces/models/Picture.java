package com.mobintum.feedplaces.models;

/**
 * Created by Ricardo on 19/05/17.
 * www.mobintum.com
 */

public class Picture {

    String path;
    Integer isMain;

    public Picture(String path, Integer isMain) {
        this.path = path;
        this.isMain = isMain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getIsMain() {
        return isMain;
    }

    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }
}
