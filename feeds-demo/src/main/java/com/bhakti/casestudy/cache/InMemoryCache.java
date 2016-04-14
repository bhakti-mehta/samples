package com.bhakti.casestudy.cache;

import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * This represents an in memory version of the cache
 *
 * @author Bhakti
 */
public class InMemoryCache implements Cache {

    // The current expiry for cache set to 60 min
    private static int EXPIRY_FOR_CACHE = 3600;

    // Create an in memory cache which evict enties after 60 min
    private com.google.common.cache.Cache<String, Object> cache =
            CacheBuilder.newBuilder().
                    expireAfterWrite(EXPIRY_FOR_CACHE, TimeUnit.SECONDS).build();

    public boolean contains(String key) {
        return cache.asMap().containsKey(key);
    }

    public Object get(String key) {
        return cache.getIfPresent(key);
    }

    public void stash(String key, Object object) {
        cache.put(key,object);

    }

    public void invalidate(String key) {
        cache.invalidate(key);

    }
}
