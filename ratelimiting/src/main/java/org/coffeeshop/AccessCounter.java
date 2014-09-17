package org.coffeeshop;

import javax.ejb.Singleton;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bhakti on 3/28/14.
 */
@Singleton
public class AccessCounter {

    private static AccessCounter accessCounter;


    private static ConcurrentHashMap<String,AccessData> accessDetails = new ConcurrentHashMap<String, AccessData>();

    public void setAccountCount(ConcurrentHashMap<String, AccessData> accountCount) {
        this.accessDetails = accountCount;
    }

    public static AccessCounter getInstance() {
        if (accessCounter == null) {
            accessCounter = new AccessCounter();

        }
        return accessCounter;

    }

    public AccessData getAccessDetails(String ipAddress) {
        return accessDetails.get(ipAddress);
    }

    public boolean contains(String ipAddress) {
        Iterator keys = (Iterator) accessDetails.keys();
        while (keys.hasNext()) {
            if (keys.next().equals(ipAddress)) return true;
        }
        return false;
    }

    public void add (String ipAddress) {
        AccessData accessData = new AccessData();
        accessData.incrementCount();
        accessData.setLastUpdated(System.currentTimeMillis());
        accessDetails.put(ipAddress, accessData);
    }

    public void increment (String ipAddress) {
        AccessData accessData = accessCounter.getAccessDetails(ipAddress);
        accessData.incrementCount();

    }


}
