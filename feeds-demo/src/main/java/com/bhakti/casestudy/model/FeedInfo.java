package com.bhakti.casestudy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;

/**
 *
 * A strongly typed object which contains the text which has be used for publishing the feed
 * @author Bhakti Mehta
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedInfo {
    @NotNull
    String tweet;

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }
}
