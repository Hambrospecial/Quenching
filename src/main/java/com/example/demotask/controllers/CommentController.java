package com.example.demotask.controllers;

import com.example.demotask.entities.Comment;
import com.example.demotask.entities.CommentLikes;
import com.example.demotask.entities.Post;
import com.example.demotask.entities.User;
import com.example.demotask.service.CommentLikeService;
import com.example.demotask.service.CommentService;
import com.example.demotask.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final PostService postService;
    private final CommentService commentService;
    private final CommentLikeService commentLikeService;

    //to make a comment
    @PostMapping("/{postId}")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment,
                                                 @PathVariable Long postId,
                                                 HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        Optional<Post> optionalPost = postService.getPostById(postId);

        if (user != null && optionalPost.isPresent()) {
            comment.setUser(user);
            optionalPost.ifPresent(comment::setPost);
            commentService.saveComment(comment);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //to get all comments in a post
    @GetMapping("/{postId}")
    public ResponseEntity<List> getAllCommentsInPost(@PathVariable Long postId, HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        Optional<Post> optionalPost = postService.getPostById(postId);

        if (user != null && optionalPost.isPresent()) {
            List<Comment> listOfComments = commentService.getAllCommentsInPost(optionalPost.get());
            return new ResponseEntity<>(listOfComments, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    //like and unlike a comment
    @PostMapping("/commentlikes/{commentId}")
    public ResponseEntity<CommentLikes> likeAndUnlikeComment(CommentLikes commentLike,
                                                             @PathVariable Long commentId,
                                                             HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        Optional<Comment> optionalComment = commentService.getCommentById(commentId);

        if (user != null && optionalComment.isPresent()) {
            Optional<CommentLikes> optionalCommentLikes = commentLikeService.findCommentLike(optionalComment.get(), user);

            if (optionalCommentLikes.isPresent()) {
                CommentLikes commentLikes = optionalCommentLikes.get();

                if (commentLikes.getUser().getUserId().equals(user.getUserId()))
                    commentLikeService.deleteCommentLike(commentLikes);
                return new ResponseEntity<>(HttpStatus.OK);
            }

            commentLike.setComment(optionalComment.get());
            commentLike.setUser(user);

            commentLikeService.likeComment(commentLike);
            return new ResponseEntity<>(commentLike, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
