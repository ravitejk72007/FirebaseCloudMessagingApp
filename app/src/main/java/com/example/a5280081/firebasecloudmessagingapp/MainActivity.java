package com.example.a5280081.firebasecloudmessagingapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a5280081.firebasecloudmessagingapp.adapter.PackageAdapter;
import com.example.a5280081.firebasecloudmessagingapp.model.Package;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.a5280081.firebasecloudmessagingapp.InstanceIdService.REG_TOKEN;
import static com.example.a5280081.firebasecloudmessagingapp.InstanceIdService.SHARED_PREFERENCE;
import static com.example.a5280081.firebasecloudmessagingapp.MyFirebaseMessagingService.ACTION;
import static com.example.a5280081.firebasecloudmessagingapp.MyFirebaseMessagingService.ACTION_ADD;
import static com.example.a5280081.firebasecloudmessagingapp.MyFirebaseMessagingService.ACTION_REMOVE;
import static com.example.a5280081.firebasecloudmessagingapp.MyFirebaseMessagingService.PACKAGE_ID;
import static com.example.a5280081.firebasecloudmessagingapp.MyFirebaseMessagingService.PACKAGE_NAME;
import static com.example.a5280081.firebasecloudmessagingapp.MyFirebaseMessagingService.SHIPPER_NAME;
import static com.example.a5280081.firebasecloudmessagingapp.model.Package.createPackageList;
import static java.util.UUID.randomUUID;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview_package) RecyclerView mPackageRecyclerView;
    @BindView(R.id.message_button) FloatingActionButton mFloatingActionButton;
    @BindView(R.id.workarea) EditText mWorkArea;
    @BindView(R.id.sendtoken) Button mSendToken;

    public static final String SENDER_ID = "704186975433";
    private static final String PICKUPREQUEST_TOPIC = "pickuprequest";
    private PackageAdapter mPackageAdapter;
    private List<Package> packageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FirebaseMessaging.getInstance().subscribeToTopic(PICKUPREQUEST_TOPIC);

        mPackageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        packageList = createPackageList();
        if (getIntent() != null){
           packageList =  performActionBasedOnIntent(getIntent(), packageList);
        }
        mPackageAdapter = new PackageAdapter(packageList);
        mPackageRecyclerView.setAdapter(mPackageAdapter);
    }


    private List<Package> performActionBasedOnIntent(Intent intent, List<Package> packageList) {
       if (intent.getStringExtra(ACTION)!= null) {
           switch (intent.getStringExtra(ACTION)) {
               case ACTION_ADD:
                   int id = Integer.parseInt(intent.getStringExtra(PACKAGE_ID));
                   String packageName = intent.getStringExtra(PACKAGE_NAME);
                   String shipperName = intent.getStringExtra(SHIPPER_NAME);
                   packageList.add(0, new Package(id, packageName, shipperName));
                   Toast.makeText(MainActivity.this, "Package with id"+ id + " is Added", Toast.LENGTH_SHORT).show();
                   break;
               case ACTION_REMOVE:
                   int idToRemove = Integer.parseInt(intent.getStringExtra(PACKAGE_ID));
                   for (Package pack : packageList) {
                       if (pack.getPackageId() == idToRemove) {
                           packageList.remove(pack);
                           Toast.makeText(MainActivity.this, "Package with id"+ idToRemove + " is Removed", Toast.LENGTH_SHORT).show();
                           break;
                       }
                   }
                   break;
               default:
                   break;
           }
       }
        return packageList;
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("Broadcast_intent"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    @OnClick(R.id.message_button)
    public void onMessageButtonClicked(){
        Intent intent = new Intent(this,MessageActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.sendtoken)
    public void sendRegistrationToken(){
        if (!mWorkArea.getText().toString().isEmpty()){
            String workArea = mWorkArea.getText().toString();
            SharedPreferences sharedPreferences = getApplication().getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
            String reg_token = sharedPreferences.getString(REG_TOKEN, null);
            Log.e("verify", reg_token+workArea);
            FirebaseMessaging fm = FirebaseMessaging.getInstance();
            fm.send(new RemoteMessage.Builder(SENDER_ID + "@gcm.googleapis.com")
                    .setMessageId(randomUUID().toString())
                    .addData("reg_token", reg_token)
                    .addData("work_area",workArea)
                    .build());
        }
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mPackageAdapter.swapAdapter(performActionBasedOnIntent(intent, packageList));
        }
    };
}
