package com.example.demotask.service;

import com.example.demotask.entities.Post;
import com.example.demotask.entities.PostLikes;
import com.example.demotask.entities.User;

import java.util.Optional;

public interface PostLikeService {
    void likePost(PostLikes like);

    void deletePostLike(PostLikes like);

    Optional<PostLikes> findPostLike(Post post, User user);

}
