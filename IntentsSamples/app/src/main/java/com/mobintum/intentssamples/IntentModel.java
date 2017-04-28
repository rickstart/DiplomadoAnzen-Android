package com.mobintum.intentssamples;

import android.graphics.drawable.Drawable;

/**
 * Created by Ricardo on 24/04/17.
 * www.mobintum.com
 */

public class IntentModel {
    private String intentName;
    private Drawable icon;
    private int action;

    public IntentModel(String intentName, Drawable icon, int action) {
        this.intentName = intentName;
        this.icon = icon;
        this.action = action;
    }

    public String getIntentName() {
        return intentName;
    }

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}
