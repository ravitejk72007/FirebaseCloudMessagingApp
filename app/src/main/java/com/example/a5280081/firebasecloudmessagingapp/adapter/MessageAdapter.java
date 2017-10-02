package com.example.a5280081.firebasecloudmessagingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a5280081.firebasecloudmessagingapp.R;
import com.example.a5280081.firebasecloudmessagingapp.model.Message;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.a5280081.firebasecloudmessagingapp.MessageActivity.OUTGOING_MESSAGE;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    public List<Message> mMessageList;

    public MessageAdapter(List<Message> messageList){
        mMessageList = messageList;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.message_listitem, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        Message message = mMessageList.get(position);
        if(message.getType()!= OUTGOING_MESSAGE){
            holder.mSenderName.setText(message.getSenderName().charAt(0));
        }else {
            holder.mSenderName.setText("ME");
        }
        holder.mMessage.setText(message.getMessageContent());
    }

    public void swapAdapter(List<Message> messageList){
        mMessageList = messageList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.sender_name) TextView mSenderName;
        @BindView(R.id.message) TextView mMessage;
        public MessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
