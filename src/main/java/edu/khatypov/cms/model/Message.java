package edu.khatypov.cms.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Message {
    private String text;
    private String color;

    public Message() {
    }

    public Message(String text, String color) {
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
