package com.mobintum.feedplaces.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Ricardo on 26/05/17.
 * www.mobintum.com
 */

public class InstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = "InstanceIdService" ;

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }
}
