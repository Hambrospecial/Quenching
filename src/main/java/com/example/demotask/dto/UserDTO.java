package com.example.demotask.dto;

import com.example.demotask.entities.User;
import lombok.Data;

@Data
public class UserDTO {
    private String message;
    private User data;
    private boolean status;

}
