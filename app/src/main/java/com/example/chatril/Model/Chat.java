package com.example.chatril.Model;

import javax.crypto.Cipher;
import javax.crypto.ExemptionMechanismException;
import javax.crypto.spec.SecretKeySpec;

public class Chat {

    private String sender;
    private String receiver;
    private String message;


    public Chat(String sender, String receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }


    public Chat(){

    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiever(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {


           return message;

    }

    public void setMessage(String message) {
        this.message = message;
    }





}
