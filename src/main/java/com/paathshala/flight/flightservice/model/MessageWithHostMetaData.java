package com.paathshala.flight.flightservice.model;

public class MessageWithHostMetaData extends HostMetaData {
    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

}
