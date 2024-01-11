package com.example.splitwise.DTO;

import com.example.splitwise.Models.Expense;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseGroupResponseDTO {
    private Expense expense;

    public ExpenseGroupResponseDTO(Expense expense) {
        this.expense = expense;
    }
}
