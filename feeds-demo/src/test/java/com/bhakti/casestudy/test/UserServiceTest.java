package com.bhakti.casestudy.test;

import com.bhakti.casestudy.config.ServiceModule;
import com.bhakti.casestudy.impl.UserServiceImpl;
import com.bhakti.casestudy.model.Feed;
import com.bhakti.casestudy.model.FeedInfo;
import com.bhakti.casestudy.repository.FeedRepository;
import com.bhakti.casestudy.repository.FeedRepository;
import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private UserServiceImpl userService;

    private FeedRepository tweetsRepository;

    @Before
    public void initMocks() {
        Injector injector =  Guice.createInjector(new ServiceModule());
        tweetsRepository = injector.getInstance(FeedRepository.class);
        userService = injector.getInstance(UserServiceImpl.class);
    }

    @Test
    public void testCreateTweet() throws Exception {

        FeedInfo tweetInfo = new FeedInfo();
        tweetInfo.setTweet("foobar");
        Feed feed = userService.createTweetObject(1,tweetInfo);
        Assert.assertNotNull(feed);
        Assert.assertEquals(tweetsRepository.getFeeds().get(0).getId(), feed.getId());
    }

}
