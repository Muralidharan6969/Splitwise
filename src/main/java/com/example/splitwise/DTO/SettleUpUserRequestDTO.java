package com.example.splitwise.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettleUpUserRequestDTO {
    private Long userId;

    public SettleUpUserRequestDTO(Long userId) {
        this.userId = userId;
    }
}
