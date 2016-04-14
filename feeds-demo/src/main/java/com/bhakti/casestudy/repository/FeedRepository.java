package com.bhakti.casestudy.repository;

import com.bhakti.casestudy.model.Feed;
import com.google.common.collect.Lists;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * This class is the in memory representation for all the feed related data
 *
 * @author Bhakti Mehta
 */

@Singleton
public class FeedRepository implements FeedsDataSource {

    /**
     * This map represents UserId -> List of Feeds mapping
     * This is an in memory representation of the data which can be replaced by a
     * DataStore
     */
    private static final ConcurrentHashMap<Integer, LinkedList<Feed>> feedsByUser =
           new ConcurrentHashMap<Integer, LinkedList<Feed>>();

    /**
     * This List represents all the Feeds which can be paginated and displayed
     */
    private static final List feeds = Collections.synchronizedList(new LinkedList<Feed>());

    private static final int USERS = 1000;

    public FeedRepository() {
        populateFeeds();

    }

    /**
     * Populate the Datastore with some initial data
     */
    public void populateFeeds() {
        for (int i = 0; i < 400; i++) {
            Integer userId = new Random().nextInt(USERS);
            String text = ("Some feed " + new Random().nextInt(USERS));
            Feed feed = addFeed(userId, text);
            System.out.println("Added" + feed);
        }
    }

    public Feed addFeed(Integer userId, String text) {
        Feed feed = new Feed();
        feed.setTweeterId(userId);
        feed.setText(text);
        LinkedList<Feed> listOfTweetsByUser = feedsByUser.get(feed.getTweeterId());
        if (listOfTweetsByUser == null) {
            listOfTweetsByUser = new LinkedList<Feed>();
        }
        listOfTweetsByUser.add(feed);
        feedsByUser.put(feed.getTweeterId(), listOfTweetsByUser);

        feeds.add(feed);
        return feed;

    }

    public List<Feed> getFeeds() {
        List<Feed> reverseList = Lists.reverse(
                Lists.newArrayList(feeds));
        return reverseList;
    }

    public List<Feed> getFeedsByUserId(Integer id) {
        return feedsByUser.get(id);
    }
}
