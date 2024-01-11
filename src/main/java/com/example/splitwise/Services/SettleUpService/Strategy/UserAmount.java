package com.example.splitwise.Services.SettleUpService.Strategy;

import com.example.splitwise.Models.UserProfile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAmount {
    private UserProfile userProfile;
    private int totalAmount;
}
