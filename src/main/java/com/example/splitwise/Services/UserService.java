package com.example.splitwise.Services;

import com.example.splitwise.Models.UserProfile;
import com.example.splitwise.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserProfile createUser(String userName, String phoneNo, String emailId, String password){
        UserProfile userProfile = new UserProfile();
        userProfile.setUserName(userName);
        userProfile.setPhoneNo(phoneNo);
        userProfile.setEmailId(emailId);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userProfile.setPassword(bCryptPasswordEncoder.encode(password));
        return userRepository.save(userProfile);
    }
}
