package com.example.splitwise.DTO;

import com.example.splitwise.Models.Expense;
import com.example.splitwise.Models.ExpenseUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseIndResponseDTO {
    private Expense expense;

    public ExpenseIndResponseDTO(Expense expense) {
        this.expense = expense;
    }
}
