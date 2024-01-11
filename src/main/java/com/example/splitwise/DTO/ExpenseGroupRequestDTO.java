package com.example.splitwise.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseGroupRequestDTO {
    private Long groupId;

    public ExpenseGroupRequestDTO(Long groupId) {
        this.groupId = groupId;
    }
}
