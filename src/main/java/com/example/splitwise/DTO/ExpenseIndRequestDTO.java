package com.example.splitwise.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseIndRequestDTO {
    private Long userId;

    public ExpenseIndRequestDTO(Long userId) {
        this.userId = userId;
    }
}
