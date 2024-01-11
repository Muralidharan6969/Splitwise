package com.example.splitwise.DTO;

import com.example.splitwise.Models.Expense;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpUserResponseDTO {
    private List<Expense> expenseSuggestion;
    public SettleUpUserResponseDTO(List<Expense> expenses){
        this.expenseSuggestion = expenses;
    }
}
