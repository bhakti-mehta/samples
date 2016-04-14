package com.bhakti.casestudy.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * This is the DAO object for the Feed posted by the user
 * A Feed can contain the following
 * a unique id
 * text
 * date of the feed
 * user id who posted the feed
 * @author Bhakti Mehta
 */
public class Feed {

    private String text;

    private UUID id;

    private Integer tweeterId;

    private Date date = new Date();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getTweeterId() {
        return tweeterId;
    }

    public void setTweeterId(Integer tweeterId) {
        this.tweeterId = tweeterId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Feed() {
        id = UUID.randomUUID();
    }

    private SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");

    public String getDate() {
        return format.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id").append(id).append(' ');
        sb.append("User: ").append(tweeterId).append(' ')
                .append("Text: ").append(text).append(' ')
                .append("Date").append(format.format(date)).append(' ');

        return sb.toString();
    }
}
