package com.example.a5280081.firebasecloudmessagingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class InstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "InstanceIdService";
    public SharedPreferences mSharedPreferences;
    public static final String SHARED_PREFERENCE = "sharedprefs";
    public static final String REG_TOKEN = "reg_token";
    
    @Override
    public void onTokenRefresh() {
        String registration_token = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, registration_token);
        mSharedPreferences = getApplication().getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(REG_TOKEN, registration_token).commit();
    }
}
