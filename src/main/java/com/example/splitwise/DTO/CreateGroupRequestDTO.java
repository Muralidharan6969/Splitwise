package com.example.splitwise.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateGroupRequestDTO {
    private String groupName;
    private Long groupAdmin;
    private List<Long> groupMembers;

    public CreateGroupRequestDTO(String groupName, Long groupAdmin, List<Long> groupMembers) {
        this.groupName = groupName;
        this.groupAdmin = groupAdmin;
        this.groupMembers = groupMembers;
    }
}
