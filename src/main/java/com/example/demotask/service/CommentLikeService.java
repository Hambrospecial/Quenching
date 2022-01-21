package com.example.demotask.service;

import com.example.demotask.entities.Comment;
import com.example.demotask.entities.CommentLikes;
import com.example.demotask.entities.User;

import java.util.Optional;

public interface CommentLikeService {
    void likeComment(CommentLikes like);

    void deleteCommentLike(CommentLikes like);

    Optional<CommentLikes> findCommentLike(Comment comment, User user);
}
