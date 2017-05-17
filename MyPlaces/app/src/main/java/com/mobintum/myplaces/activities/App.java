package com.mobintum.myplaces.activities;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Ricardo on 16/05/17.
 * www.mobintum.com
 */

public class App extends Application {
    private RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public void setRequestQueue(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }
}
