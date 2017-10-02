package com.example.a5280081.firebasecloudmessagingapp;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String ACTION = "action";
    public static final String ACTION_ADD = "ADD";
    public static final String ACTION_REMOVE = "REMOVE";
    public static final String PACKAGE_NAME = "packagename";
    public static final String SHIPPER_NAME = "shippername";
    public static final String PACKAGE_ID = "packageid";
    private static final String TAG = "MyFirebaseMessagingServ";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Intent intent = new Intent("Broadcast_intent");
        intent.putExtra(ACTION, remoteMessage.getData().get(ACTION));
        intent.putExtra(PACKAGE_ID, remoteMessage.getData().get(PACKAGE_ID));
        intent.putExtra(PACKAGE_NAME, remoteMessage.getData().get(PACKAGE_NAME));
        intent.putExtra(SHIPPER_NAME, remoteMessage.getData().get(SHIPPER_NAME));
        LocalBroadcastManager localBroadcastManager =  LocalBroadcastManager.getInstance(this);
        localBroadcastManager.sendBroadcast(intent);

    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
        Log.e(TAG, "sent successfully");
    }

    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);
        Log.e(TAG, "sent gone wrong");
    }
}
