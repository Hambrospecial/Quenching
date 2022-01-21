package com.example.demotask.controllers;

import com.example.demotask.dto.ResponseDTO;
import com.example.demotask.entities.User;
import com.example.demotask.service.FollowerService;
import com.example.demotask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowerController {
    private final FollowerService followerService;
    private final UserService userService;

    //to follow and unfollow another user
    @PostMapping("/follow/{id}")
    public ResponseEntity<ResponseDTO> followUser(@PathVariable Long id, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("loggedUser");

        //http server response
        ResponseDTO response = new ResponseDTO();
        if (user == null) {
            response.setStatusCode(401);
            response.setMessage("user not logged in!!!");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        Optional<User> optionalUser = userService.getUserById(id);

        if (!user.getUserId().equals(id) && optionalUser.isPresent()) {
            String message = followerService.followUser(id, user);
            if (message.equals("You have made a new connection") || message.equals("You have unfollowed a connection")) {
                response.setStatusCode(201);
                response.setMessage(message);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } else if (user.getUserId().equals(id)) {
            response.setStatusCode(301);
            response.setMessage("Cannot Follow Self");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }

        response.setStatusCode(500);
        response.setMessage("Server Error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    //to get the followers of a user
    @GetMapping("/followers")
    public ResponseEntity<?> getFollowers(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("loggedUser");

        //http server response
        ResponseDTO response = new ResponseDTO();
        var data = followerService.getFollowersOfUser(user);
        if (user == null) {
            response.setStatusCode(401);
            response.setMessage("user not logged in!!!");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(followerService.getFollowersOfUser(user), HttpStatus.OK);
    }


    //to get the users followed by a user
    @GetMapping("/followees")
    public ResponseEntity<?> getUsersFollowedByUser(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("loggedUser");

        //http server response
        ResponseDTO response = new ResponseDTO();
        if (user == null) {
            response.setStatusCode(401);
            response.setMessage("user not login!!!");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(followerService.getUsersFollowedByUser(user), HttpStatus.OK);
    }

}
