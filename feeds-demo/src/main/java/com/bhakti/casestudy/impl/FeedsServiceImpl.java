package com.bhakti.casestudy.impl;

import com.bhakti.casestudy.api.FeedsService;
import com.bhakti.casestudy.model.Feed;
import com.bhakti.casestudy.repository.FeedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * This is the implementing class for the <code>FeedService</code>
 * This will get the most recent feeds and display them
 *
 * @author  Bhakti Mehta
 */
@Singleton
public class FeedsServiceImpl implements FeedsService {
    Logger logger = LoggerFactory.getLogger(FeedsServiceImpl.class);

    /*
     * This is the repository for storing the tweets related data
     */
    private FeedRepository feedRepository;

    @Inject
    public FeedsServiceImpl(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    /**
     * This method returns the last 100 feeds
     * @return The most recent feeds
     */
    public Response showFeeds(int page, int limit) {
        return Response.ok (getMostRecentFeeds(page, limit), MediaType.APPLICATION_JSON_TYPE).build();
    }

    public List<Feed> getMostRecentFeeds(int page, int limit) {
        List<Feed> feeds = feedRepository.getFeeds();

        int index = (page-1)*limit;
        logger.debug("Returning items from " + index + " to "+ (index+limit) );
        return feeds.subList(index, index+limit);
    }


}
