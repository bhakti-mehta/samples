package com.bhakti.casestudy.repository;

import com.bhakti.casestudy.model.Feed;

import java.util.List;

/**
 * This is top level abstraction for the Feeds source
 * This can have multiple implementations for various DB types
 *
 * @author Bhakti Mehta
 */
public interface FeedsDataSource {
    public Feed addFeed(Integer userId, String text) ;

    public List<Feed> getFeeds() ;
}
