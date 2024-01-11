package com.example.splitwise.Services.SettleUpService;

import com.example.splitwise.Models.Expense;
import com.example.splitwise.Models.ExpenseUser;
import com.example.splitwise.Models.UserProfile;
import com.example.splitwise.Repositories.ExpenseUserRepository;
import com.example.splitwise.Repositories.UserRepository;
import com.example.splitwise.Services.SettleUpService.Strategy.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserSettleUpService {
    private UserRepository userRepository;
    private ExpenseUserRepository expenseUserRepository;
    private SettleUpStrategy settleUpStrategy;

    @Autowired
    UserSettleUpService(UserRepository userRepository, ExpenseUserRepository expenseUserRepository,
                        SettleUpStrategy settleUpStrategy){
        this.userRepository = userRepository;
        this.expenseUserRepository = expenseUserRepository;
        this.settleUpStrategy = settleUpStrategy;
    }
    public List<Expense> settleUpUser(Long userId) throws Exception {
        //Retrieving UserProfile Object
        Optional<UserProfile> userProfile = userRepository.findById(userId);
        if(userProfile.isEmpty()){
            throw new Exception();
        }

        //Retrieving ExpenseUser object
        List<ExpenseUser> expenseUsers = expenseUserRepository.findAllByUserProfile(userProfile.get());

        //Extracting transaction suggestions
        Set<Expense> expenseSet = new HashSet<>();
        for(ExpenseUser eu : expenseUsers){
            expenseSet.add(eu.getExpense());
        }
        List<Expense> transactions = settleUpStrategy.settleUpUserHelper(expenseSet.stream().toList());

        //Cross-checking the expenses
        List<Expense> expensesToReturn = new ArrayList<>();
        for(Expense e : transactions){
            for(ExpenseUser eu : e.getExpenseUsers()){
                if(eu.getUserProfile().equals(userProfile.get())){
                    expensesToReturn.add(e);
                }
            }
        }
        return expensesToReturn;
    }

}
