package com.mascota.yanamascotavirtual;

public class Message {
    private String message;
    private boolean isSentByUser;

    public Message(String message, boolean isSentByUser) {
        this.message = message;
        this.isSentByUser = isSentByUser;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSentByUser() {
        return isSentByUser;
    }
}