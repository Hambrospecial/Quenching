package com.example.demotask.service;

import com.example.demotask.entities.User;

import java.util.List;

public interface FollowerService {
    String followUser(Long followeeId, User follower);

    List<User> getFollowersOfUser(User user);

    List<User> getUsersFollowedByUser(User user);

}
