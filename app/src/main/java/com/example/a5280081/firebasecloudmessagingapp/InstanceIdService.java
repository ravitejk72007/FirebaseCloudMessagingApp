package com.example.a5280081.firebasecloudmessagingapp;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class InstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "InstanceIdService";
    
    @Override
    public void onTokenRefresh() {
        String registration_token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, registration_token);
    }
}
