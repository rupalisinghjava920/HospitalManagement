package com.health.management.helper;


public class Message {
    public String content;
    public String type;


    public Message(String content, String type) {
        this.content = content;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public Message() {
        super();

    }
}
