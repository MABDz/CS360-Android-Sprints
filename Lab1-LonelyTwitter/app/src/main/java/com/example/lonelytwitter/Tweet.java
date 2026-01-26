package com.example.lonelytwitter;

import java.util.Date;

public abstract class Tweet implements Tweetable {
    private Date date;
    private String message;

    public Tweet(String message, Date date) {
        this.date = date;
        this.message = message;
    }

    public Tweet(String message) {
        this.date = new Date();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public abstract Boolean isImportant();
}
