package com.example.demotask.repositories;

import com.example.demotask.entities.Favorites;
import com.example.demotask.entities.Post;
import com.example.demotask.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorites, Long> {
    Favorites findByPostAndUser(Post post, User user);

    List<Favorites> findAllByUser(User user);
}
