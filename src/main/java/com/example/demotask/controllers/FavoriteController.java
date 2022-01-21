package com.example.demotask.controllers;

import com.example.demotask.entities.Favorites;
import com.example.demotask.entities.Post;
import com.example.demotask.entities.User;
import com.example.demotask.service.FavoriteService;
import com.example.demotask.service.PostService;
import com.example.demotask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/favourite")
@RequiredArgsConstructor
public class FavoriteController {

    private final PostService postService;
    private final FavoriteService favoriteService;
    private final UserService userService;

    //Star a post or add to favourite list
    @PostMapping("/{postId}")
    public ResponseEntity<Favorites> addPostToFavorite(@PathVariable(name = "postId") Long postId,
                                                       HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user != null) {
            Optional<Post> post = postService.getPostById(postId);
            if (post.isPresent()) {
                Favorites favorite = favoriteService.getByPostAndUser(post.get(), user);
                if (favorite == null) {
                    Favorites favourite1 = new Favorites();
                    favourite1.setUser(user);
                    favourite1.setPost(post.get());
                    Favorites favorite2 = favoriteService.saveFavorite(favourite1);
                    return new ResponseEntity<>(favorite2, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //get all starred posts by user
    @GetMapping()
    public ResponseEntity<List> getAllFavouritePosts(HttpSession session) {

        User user = (User) session.getAttribute("loggedUser");

        if (user != null) {
            List<Favorites> listOfFavorites = favoriteService.findAllByUser(user);
            List<Post> listOfPosts = new ArrayList<>();
            for (Favorites favorite : listOfFavorites)
                listOfPosts.add(favorite.getPost());
            return new ResponseEntity<>(listOfPosts, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // delete post from favourite list
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePostFromFavorite(HttpSession session, @PathVariable(name = "postId") Long postId) {
        User user = (User) session.getAttribute("loggedUser");
        Optional<Post> post = postService.getPostById(postId);

        if (user != null && post.isPresent()) {

            Favorites favorite = favoriteService.getByPostAndUser(post.get(), user);

            if (favorite != null) {
                favoriteService.deleteFavorite(favorite);
                return new ResponseEntity<>(HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}

