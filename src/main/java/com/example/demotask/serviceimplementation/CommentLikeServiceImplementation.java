package com.example.demotask.serviceimplementation;

import com.example.demotask.entities.Comment;
import com.example.demotask.entities.CommentLikes;
import com.example.demotask.entities.User;
import com.example.demotask.repositories.CommentLikeRepository;
import com.example.demotask.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentLikeServiceImplementation implements CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;


    @Override
    public void likeComment(CommentLikes like) {
        commentLikeRepository.save(like);
    }

    @Override
    public void deleteCommentLike(CommentLikes like) {
        commentLikeRepository.delete(like);
    }

    @Override
    public Optional<CommentLikes> findCommentLike(Comment comment, User user) {
        return commentLikeRepository.findByCommentAndUser(comment, user);

    }
}
