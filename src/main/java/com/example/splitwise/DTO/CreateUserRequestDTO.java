package com.example.splitwise.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequestDTO {
    private String userName;
    private String phoneNo;
    private String emailId;
    private String password;

    public CreateUserRequestDTO(String userName, String phoneNo, String emailId, String password) {
        this.userName = userName;
        this.phoneNo = phoneNo;
        this.emailId = emailId;
        this.password = password;
    }
}
