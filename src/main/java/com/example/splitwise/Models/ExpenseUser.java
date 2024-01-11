package com.example.splitwise.Models;

import com.example.splitwise.Models.ENUMS.ExpenseUserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ExpenseUser extends BaseModel{
    @ManyToOne
    private Expense expense;
    @ManyToOne
    private UserProfile userProfile;
    private int amountInvolved;
    @Enumerated(EnumType.ORDINAL)
    private ExpenseUserType expenseUserType;

    public ExpenseUser(Expense expense, UserProfile userProfile, int amountInvolved, ExpenseUserType expenseUserType) {
        this.expense = expense;
        this.userProfile = userProfile;
        this.amountInvolved = amountInvolved;
        this.expenseUserType = expenseUserType;
    }

    public ExpenseUser() {
    }
}
