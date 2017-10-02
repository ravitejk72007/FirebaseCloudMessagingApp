package com.example.a5280081.firebasecloudmessagingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.example.a5280081.firebasecloudmessagingapp.adapter.MessageAdapter;
import com.example.a5280081.firebasecloudmessagingapp.model.Message;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.a5280081.firebasecloudmessagingapp.MainActivity.SENDER_ID;
import static java.util.UUID.randomUUID;

public class MessageActivity extends AppCompatActivity {

    public static final int INCOMING_MESSAGE = 0;
    public static final int OUTGOING_MESSAGE = 1;
    private MessageAdapter mMessageAdapter;

    @BindView(R.id.message_recyclerview) RecyclerView mMessageRecyclerView;
    @BindView(R.id.edit_message) EditText mTypeMessage;
    @BindView(R.id.send) Button mSend;
    public static List<Message> mMessageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        mMessageList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMessageRecyclerView.setLayoutManager(linearLayoutManager);
        mMessageRecyclerView.setHasFixedSize(true);
        mMessageAdapter = new MessageAdapter(mMessageList);
        mMessageRecyclerView.setAdapter(mMessageAdapter);
    }

    @OnClick(R.id.send)
    public void onSendClicked(){
        if(!mTypeMessage.getText().toString().isEmpty()){
            mMessageList.add(new Message(mTypeMessage.getText().toString(), "ME", OUTGOING_MESSAGE));
            mMessageAdapter.swapAdapter(mMessageList);
            sendMessage(mTypeMessage.getText().toString(), "1234");
            mTypeMessage.setText("");
        }
    }

    private void sendMessage(String msg, String to) {
        FirebaseMessaging fm = FirebaseMessaging.getInstance();
        fm.send(new RemoteMessage.Builder(SENDER_ID + "@gcm.googleapis.com")
                .setMessageId(randomUUID().toString())
                .addData("message", msg)
                .addData("work_area",to)
                .build());
    }
}
