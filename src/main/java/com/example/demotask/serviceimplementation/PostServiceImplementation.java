package com.example.demotask.serviceimplementation;


import com.example.demotask.entities.Post;
import com.example.demotask.entities.User;
import com.example.demotask.repositories.CommentRepository;
import com.example.demotask.repositories.PostLikeRepository;
import com.example.demotask.repositories.PostRepository;
import com.example.demotask.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImplementation implements PostService {

    private final PostRepository postRepository;
    private final PostLikeRepository likeRepository;
    private final CommentRepository commentRepository;

    public void addPost(User user, Post post) {
        post.setUser(user);
        postRepository.save(post);
    }

    @Override
    public List<Post> getAllPostsByUser(User user) {
        return postRepository.findAllByUser(user);
    }


    @Override
    public void updatePost(Post post) {
        postRepository.save(post);
    }


    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }


    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findByPostId(id);
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }
}
