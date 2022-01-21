package com.example.demotask.serviceimplementation;

import com.example.demotask.entities.Favorites;
import com.example.demotask.entities.Post;
import com.example.demotask.entities.User;
import com.example.demotask.repositories.FavoriteRepository;
import com.example.demotask.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImplementation implements FavoriteService {

    private final FavoriteRepository favoriteRepository;



    @Override
    public Favorites getByPostAndUser(Post post, User user) {
        return favoriteRepository.findByPostAndUser(post, user);
    }

    @Override
    public Favorites saveFavorite(Favorites favourite) {
        return favoriteRepository.save(favourite);
    }

    @Override
    public List<Favorites> findAllByUser(User user) {
        return favoriteRepository.findAllByUser(user);
    }

    @Override
    public void deleteFavorite(Favorites favorite) {
        favoriteRepository.delete(favorite);
    }
}
