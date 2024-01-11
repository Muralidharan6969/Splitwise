package com.example.splitwise.Services;

import com.example.splitwise.Models.ENUMS.ExpenseType;
import com.example.splitwise.Models.ENUMS.ExpenseUserType;
import com.example.splitwise.Models.Expense;
import com.example.splitwise.Models.ExpenseUser;
import com.example.splitwise.Models.UserProfile;
import com.example.splitwise.Repositories.ExpenseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseUserService {
    private ExpenseUserRepository expenseUserRepository;
    @Autowired
    public ExpenseUserService(ExpenseUserRepository expenseUserRepository) {
        this.expenseUserRepository = expenseUserRepository;
    }

    public ExpenseUser createExpenseUser(Expense expense, UserProfile userProfile, ExpenseUserType expenseUserType,
                                  int amount){
        ExpenseUser expenseUser = new ExpenseUser(expense, userProfile, amount, expenseUserType);
        return expenseUserRepository.save(expenseUser);
    }
}
