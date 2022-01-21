package com.example.demotask.repositories;

import com.example.demotask.entities.Post;
import com.example.demotask.entities.PostLikes;
import com.example.demotask.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLikes, Long> {
    Optional<PostLikes> findByPostAndUser(Post post, User user);
}
