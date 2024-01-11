package com.example.splitwise.DTO;

import com.example.splitwise.Models.UserProfile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponseDTO {
    private UserProfile userProfile;

    public CreateUserResponseDTO(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
