package com.bhakti.casestudy.repository;

import com.bhakti.casestudy.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class represents the in memory data source for User related data
 *
 * @author Bhakti Mehta
 */
public class UserRepository implements UserDataSource {

    private static final Map<Integer, User> userData =  new ConcurrentHashMap<Integer, User>();

    private static final int USERS = 1000;

    private String[] firstNames = {"Bob", "Jill", "Tom", "Brandon", "Mary", "Lily", "Jane", "John", "Amy", "Ash"};

    private String[] lastNames = {"Joel", "Doe", "Brown", "Black", "Forest", "Foo", "Trump", "Simon", "Red", "Green"};

    public User addUser(User user) {
        userData.put(user.getId(), user);
        return user;
    }

    public void populateUsers() {
        List<String> firstNameList = Arrays.asList(firstNames);
        List<String> lastNameList = Arrays.asList(lastNames);

        for (int i = 0 ; i <100 ; i ++ ) {
            Integer userId = new Random().nextInt(USERS);
            User user = new User();
            user.setId(userId);
            String firstName = firstNameList.get(new Random().nextInt(firstNameList.size()));
            user.setFirstName(firstName);
            String lastName = lastNameList.get(new Random().nextInt(lastNameList.size()));
            user.setLastName(lastName);
            String uniqueId = firstName + lastName + userId;
            user.setTwitterHandle("@" + uniqueId);
            user.setUrlPath("https://s3.someurl/" + uniqueId);

            addUser(user);
            System.out.println("Added user " + user);

        }

    }

    public User getUser(Integer id) {
        return userData.get(id);
    }

}
