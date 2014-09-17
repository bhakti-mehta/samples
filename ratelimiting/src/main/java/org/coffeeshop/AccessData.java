package org.coffeeshop;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by bhakti on 3/28/14.
 */
public class AccessData {
    private long lastUpdated;
    private AtomicInteger count;

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(AtomicInteger count) {
        this.count = count;
    }

    public int incrementCount() {
        return count.getAndIncrement();
    }

    public AccessData() {
        count = new AtomicInteger(0);
    }
}
