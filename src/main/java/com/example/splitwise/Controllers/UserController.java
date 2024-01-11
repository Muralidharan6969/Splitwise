package com.example.splitwise.Controllers;

import com.example.splitwise.DTO.CreateUserRequestDTO;
import com.example.splitwise.DTO.CreateUserResponseDTO;
import com.example.splitwise.Models.UserProfile;
import com.example.splitwise.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public CreateUserResponseDTO createUser(CreateUserRequestDTO userRequestDTO){
        UserProfile userProfile = userService.createUser(userRequestDTO.getUserName(),
                userRequestDTO.getPhoneNo(), userRequestDTO.getEmailId(),
                userRequestDTO.getPassword());
        return new CreateUserResponseDTO(userProfile);
    }
}
