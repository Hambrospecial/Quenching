package com.example.demotask.repositories;

import com.example.demotask.entities.Post;
import com.example.demotask.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostId(Long id);

    List<Post> findAllByUser(User user);
}
