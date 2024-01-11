package com.example.splitwise.DTO;

import com.example.splitwise.Models.GroupSplitwise;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupResponseDTO {
    private GroupSplitwise groupSplitwise;

    public CreateGroupResponseDTO(GroupSplitwise groupSplitwise) {
        this.groupSplitwise = groupSplitwise;
    }
}
