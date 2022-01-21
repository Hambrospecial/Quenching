package com.example.demotask.serviceimplementation;

import com.example.demotask.entities.Post;
import com.example.demotask.entities.PostLikes;
import com.example.demotask.entities.User;
import com.example.demotask.repositories.PostLikeRepository;
import com.example.demotask.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeServiceImplementation implements PostLikeService {

    private final PostLikeRepository postLikeRepository;

    @Override
    public void likePost(PostLikes like) {
        postLikeRepository.save(like);
    }

    @Override
    public void deletePostLike(PostLikes like) {
        postLikeRepository.delete(like);
    }

    @Override
    public Optional<PostLikes> findPostLike(Post post, User user) {
        return postLikeRepository.findByPostAndUser(post, user);
    }
}
