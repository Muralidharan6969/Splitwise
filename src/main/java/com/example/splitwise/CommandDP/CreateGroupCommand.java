package com.example.splitwise.CommandDP;

import com.example.splitwise.Controllers.GroupController;
import com.example.splitwise.DTO.CreateGroupRequestDTO;
import com.example.splitwise.DTO.CreateGroupResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CreateGroupCommand implements Command{
    private Scanner scn = new Scanner(System.in);
    private GroupController groupController;

    public CreateGroupCommand(){}

    @Autowired
    public CreateGroupCommand(GroupController groupController) {
        this.groupController = groupController;
    }

    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split("\\s+"));
        return words.get(0).equals("create_group");
    }

    @Override
    public void execute(String input) throws Exception {
        System.out.println("Specify the Name of the Group");
        String userName = scn.next();
        System.out.println("Specify the count of users to be part of this group");
        int userCount = scn.nextInt();
        List<Long> userIds = new ArrayList<>();
        for(int i=0;i<userCount;i++){
            System.out.println("Specify the user id of the user to be added to the group");
            Long userId = scn.nextLong();
            userIds.add(userId);
        }
        System.out.println("Specify the user id of admin for this group");
        Long adminId = scn.nextLong();
        CreateGroupRequestDTO groupRequestDTO = new CreateGroupRequestDTO(userName, adminId, userIds);
        CreateGroupResponseDTO groupResponseDTO = groupController.createGroup(groupRequestDTO);

        //Displaying Group Details to the Client
        System.out.println("Group Created Successfully with the following details");
        System.out.println("Group Id: "+groupResponseDTO.getGroupSplitwise().getId());
        System.out.println("Group Name: "+groupResponseDTO.getGroupSplitwise().getGroupName());
        System.out.println("Group Admin Id: "+groupResponseDTO.getGroupSplitwise().getGroupAdmin().getUserName());
        System.out.println("Group Members: ");
        for(int i=0;i<groupResponseDTO.getGroupSplitwise().getGroupMembers().size();i++){
            System.out.println(groupResponseDTO.getGroupSplitwise().getGroupMembers().get(i).getUserName());
        }
    }

    @Override
    public List<String> getCommandName() {
        return List.of("create_group");
    }
}
