package com.bhakti.casestudy.impl;

import com.bhakti.casestudy.api.UserService;
import com.bhakti.casestudy.cache.Cache;
import com.bhakti.casestudy.model.Feed;
import com.bhakti.casestudy.model.FeedInfo;
import com.bhakti.casestudy.model.User;
import com.bhakti.casestudy.repository.FeedRepository;
import com.bhakti.casestudy.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * This is the implementation class for the UserService resource
 * This class will provide methods to get user details, follow another user
 * and post feeds
 * @author Bhakti Mehta
 */
@Singleton
public class UserServiceImpl implements UserService {

    private FeedRepository feedRepository;

    private UserRepository userRepository;

    private Cache cache;

    @Inject
    public UserServiceImpl (FeedRepository feedRepository, UserRepository userRepository, Cache cache) {
        this.feedRepository = feedRepository;
        this.userRepository = userRepository;
        this.cache = cache;
    }

    public Response getUser(Integer id) {
        String key = "User." + id;
        User user = null;
        //Check in cache first
        if (cache.contains(key)) {
            System.out.println("Returned from cache");
            user = (User)cache.get(key);
            return Response.ok(user, MediaType.APPLICATION_JSON_TYPE).build();

        }

        // Check in DB
        user = userRepository.getUser(id);

        //Store in Cache if found
        if (user != null) {
            cache.stash("User." + id, user);
            return Response.ok(user, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No User found with id " + id).build();
        }
    }

    public Response createTweet(Integer id, FeedInfo tweetInfo) {
        Feed feed = createTweetObject(id, tweetInfo);
        return Response.ok(feed,MediaType.APPLICATION_JSON_TYPE).build();

    }

    public Feed createTweetObject(Integer id, FeedInfo tweetInfo) {
        return  feedRepository.addFeed(id, tweetInfo.getTweet());
    }
    public Response getTweets(Integer id ) {
        List<Feed> tweetsByUser = feedRepository.getFeedsByUserId(id);
        return Response.ok(tweetsByUser, MediaType.APPLICATION_JSON_TYPE).build();
    }


    public Response follow( Integer id,  Integer followingId) {
        User user = userRepository.getUser(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("No User found with id " + id).build();
        }
        User following = userRepository.getUser(followingId);
        if (following == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("No User found with id " + followingId).build();
        }
        user.getFollowing().add(followingId);
        following.getFollowers().add(id);
        userRepository.addUser(user);
        userRepository.addUser(following);

        return Response.ok(user, MediaType.APPLICATION_JSON_TYPE).build();

    }




}
