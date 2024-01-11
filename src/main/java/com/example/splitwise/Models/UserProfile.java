package com.example.splitwise.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserProfile extends BaseModel{
    private String userName;
    private String phoneNo;
    private String emailId;
    private String password;
}
