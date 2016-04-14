package com.bhakti.casestudy.model;

import java.util.HashSet;
import java.util.Set;

/**
 * A DAO representing the User which can be persisted to any datastore
 * Every User will have the following details
 * id
 * firstName
 * lastName
 * twitterHandle
 * a set of followers
 * a set of people they follow
 * profile picture URL
 *
 * @author Bhakti Mehta
 */
public class User {
    private Integer id;

    private String firstName;

    private String lastName;

    private String twitterHandle;

    private Set<Integer> followers = new HashSet<Integer>();

    private Set<Integer> following = new HashSet<Integer>();

    private String urlPath;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Integer> followers) {
        this.followers = followers;
    }

    public Set<Integer> getFollowing() {
        return following;
    }

    public void setFollowing(Set<Integer> following) {
        this.following = following;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
    @Override
    public String toString() {
     StringBuilder sb = new StringBuilder();
        sb.append("id ").append(id).append(' ');
        sb.append("FirstName: ").append(firstName).append(' ')
                .append("LastName: ").append(lastName).append(' ')
                .append("Twitter ").append(twitterHandle).append(' ')
                .append("Profile pic ").append(urlPath).append(' ')
                .append("Following ").append(following.size()).append(' ')
                .append("Followers ").append(followers.size());

        return sb.toString();
    }

}
