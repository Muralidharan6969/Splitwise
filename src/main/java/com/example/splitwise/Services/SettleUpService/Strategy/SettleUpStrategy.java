package com.example.splitwise.Services.SettleUpService.Strategy;

import com.example.splitwise.Models.Expense;
import com.example.splitwise.Models.ExpenseUser;
import com.example.splitwise.Models.UserProfile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SettleUpStrategy {
    public List<Expense> settleUpHelper(List<Expense> expenseList, List<UserProfile> userProfileList);
    public List<Expense> settleUpUserHelper(List<Expense> expenseList);
    public List<Expense> settleUpGroupHelper(List<Expense> expenseList, List<UserProfile> groupMembers);
}
