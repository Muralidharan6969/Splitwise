package com.example.splitwise.Models;

import com.example.splitwise.Models.ENUMS.ExpenseType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Expense extends BaseModel{
    private String expenseDescription;
    private int expenseTotalAmount;
    @OneToMany (fetch = FetchType.EAGER, mappedBy = "expense", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExpenseUser> expenseUsers = new ArrayList<>();
    @Enumerated(EnumType.ORDINAL)
    private ExpenseType expenseType;
    @ManyToOne
    private UserProfile createdBy;
    @ManyToOne
    private GroupSplitwise groupSplitwise;

    public Expense(String expenseDescription, int expenseTotalAmount, ExpenseType expenseType,
                   UserProfile createdBy, GroupSplitwise groupSplitwise) {
        this.expenseDescription = expenseDescription;
        this.expenseTotalAmount = expenseTotalAmount;
        this.expenseType = expenseType;
        this.createdBy = createdBy;
        this.groupSplitwise = groupSplitwise;
    }
}
