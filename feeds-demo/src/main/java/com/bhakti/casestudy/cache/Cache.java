package com.bhakti.casestudy.cache;

/**
 * This specifies a contract for a generic key value store
 *
 * @author Bhakti Mehta
 */
public interface Cache {

    public boolean contains(String key);

    public Object get(String key);

    public void stash(String key, Object object);

    public void invalidate (String key);
}
