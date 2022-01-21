package com.example.demotask.repositories;

import com.example.demotask.entities.Comment;
import com.example.demotask.entities.CommentLikes;
import com.example.demotask.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLikes, Long> {
    Optional<CommentLikes> findByCommentAndUser(Comment comment, User user);

}
