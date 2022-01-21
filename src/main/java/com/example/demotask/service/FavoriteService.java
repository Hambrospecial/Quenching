package com.example.demotask.service;

import com.example.demotask.entities.Favorites;
import com.example.demotask.entities.Post;
import com.example.demotask.entities.User;

import java.util.List;

public interface FavoriteService {
    Favorites getByPostAndUser(Post post, User user);

    Favorites saveFavorite(Favorites favourite);

    List<Favorites> findAllByUser(User user);

    void deleteFavorite(Favorites favorite);
}
