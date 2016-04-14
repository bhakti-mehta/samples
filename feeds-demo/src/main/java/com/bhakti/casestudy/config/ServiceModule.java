package com.bhakti.casestudy.config;

import com.bhakti.casestudy.cache.Cache;
import com.bhakti.casestudy.cache.InMemoryCache;
import com.bhakti.casestudy.repository.FeedRepository;
import com.bhakti.casestudy.repository.UserRepository;
import com.google.inject.AbstractModule;

/**
 * This class is used to configure the repositories which
 * represent the User and Feeds data
 *
 * @author Bhakti Mehta
 */
public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {

        FeedRepository feedRepository = new FeedRepository();
        bind(FeedRepository.class).toInstance(feedRepository);
        feedRepository.populateFeeds();

        UserRepository userRepository = new UserRepository();
        userRepository.populateUsers();
        bind(UserRepository.class).toInstance(userRepository);

        InMemoryCache cache = new InMemoryCache();
        bind(Cache.class).toInstance(cache);
    }

}
