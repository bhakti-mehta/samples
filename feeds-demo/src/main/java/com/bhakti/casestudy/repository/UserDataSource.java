package com.bhakti.casestudy.repository;

import com.bhakti.casestudy.model.User;

/**
 * This is the top level abstraction for the Data source
 * This can have multiple implementations for various DB types
 *
 * @author Bhakti Mehta
 */
public interface UserDataSource {

    public User getUser(Integer id);


}
