package com.example.splitwise.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettleUpGroupRequestDTO {
    private Long groupId;

    public SettleUpGroupRequestDTO(Long groupId) {
        this.groupId = groupId;
    }
}
