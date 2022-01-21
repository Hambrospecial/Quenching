package com.example.demotask.service;

import com.example.demotask.entities.Comment;
import com.example.demotask.entities.Post;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    void saveComment(Comment comment);

    Optional<Comment> getCommentById(Long id);

    List<Comment> getAllCommentsInPost(Post post);
}
