package com.example.demotask.serviceimplementation;

import com.example.demotask.entities.Comment;
import com.example.demotask.entities.Post;
import com.example.demotask.repositories.CommentRepository;
import com.example.demotask.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImplementation implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findByCommentId(id);
    }

    @Override
    public List<Comment> getAllCommentsInPost(Post post) {
        return commentRepository.findByPost(post);
    }
}
