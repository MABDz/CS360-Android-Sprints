package com.example.lonelytwitter;

import java.util.Date;

public class ImportantTweet extends Tweet {
    public ImportantTweet(String message, Date date) {
        super(message, date);
    }

    public ImportantTweet(String message) {
        super(message);
    }

    @Override
    public Boolean isImportant() {
        return Boolean.TRUE;
    }
}
