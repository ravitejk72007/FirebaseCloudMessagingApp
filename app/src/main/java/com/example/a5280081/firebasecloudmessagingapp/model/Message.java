package com.example.a5280081.firebasecloudmessagingapp.model;


public class Message {

    private String messageContent;
    private String senderName;
    private int type;

    public Message(String messageContent, String senderName, int type) {
        this.messageContent = messageContent;
        this.senderName = senderName;
        this.type = type;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
