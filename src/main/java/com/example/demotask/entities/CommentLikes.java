package com.example.demotask.entities;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "commentLikes")
@Data
public class CommentLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postLikeId;


    @ManyToOne
    private Comment comment;

    @ManyToOne
    private User user;
}
