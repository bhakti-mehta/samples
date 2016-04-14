package com.bhakti.casestudy.test;

import com.bhakti.casestudy.config.ServiceModule;
import com.bhakti.casestudy.impl.FeedsServiceImpl;
import com.bhakti.casestudy.model.Feed;
import com.bhakti.casestudy.repository.FeedRepository;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.List;

/**
 * Basic test for feeds
 * @author  Bhakti Mehta
 */
@RunWith(MockitoJUnitRunner.class)
public class FeedsResourceTest {

    @Before
    public void initMocks() {
        Injector injector =  Guice.createInjector(new ServiceModule());
        tweetsRepository = injector.getInstance(FeedRepository.class);
        feedsService = injector.getInstance(FeedsServiceImpl.class);
    }

    private FeedsServiceImpl feedsService;

    private FeedRepository tweetsRepository;

    @Test
    public void getFeeds() {

        Assert.assertNotNull(feedsService);
        Assert.assertNotNull(tweetsRepository);
        List<Feed> feeds = feedsService.getMostRecentFeeds(2, 10);
        Assert.assertNotNull(feeds);
        Assert.assertTrue(feeds.size() == 10);

        feeds = feedsService.getMostRecentFeeds(1,50);
        Assert.assertTrue(feeds.size()==50);



    }
}
