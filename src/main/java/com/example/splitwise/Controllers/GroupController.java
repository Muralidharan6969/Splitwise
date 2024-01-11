package com.example.splitwise.Controllers;

import com.example.splitwise.DTO.CreateGroupRequestDTO;
import com.example.splitwise.DTO.CreateGroupResponseDTO;
import com.example.splitwise.Models.GroupSplitwise;
import com.example.splitwise.Services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GroupController {
    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    public CreateGroupResponseDTO createGroup(CreateGroupRequestDTO groupRequestDTO) throws Exception {
        GroupSplitwise groupSplitwise = groupService.createGroup(groupRequestDTO.getGroupName(),
                groupRequestDTO.getGroupAdmin(), groupRequestDTO.getGroupMembers());
        return new CreateGroupResponseDTO(groupSplitwise);
    }
}
