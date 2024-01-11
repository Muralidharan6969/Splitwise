package com.example.splitwise.CommandDP;

import com.example.splitwise.Controllers.UserController;
import com.example.splitwise.DTO.CreateUserRequestDTO;
import com.example.splitwise.DTO.CreateUserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CreateUserCommand implements Command{
    private UserController userController;
    private Scanner scn = new Scanner(System.in);

    public CreateUserCommand(){}

    @Autowired
    public CreateUserCommand(UserController userController){
        this.userController = userController;
    }
    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split("\\s+"));
        return words.get(0).equals("create_user");
    }

    @Override
    public void execute(String input) {
        System.out.println("Specify the Name of the user");
        String userName = scn.next();
        System.out.println("Specify the Phone No of the user");
        String phoneNo = scn.next();
        System.out.println("Specify the Email Id of the user");
        String emailId = scn.next();
        System.out.println("Specify the password of the user");
        String password = scn.next();
        CreateUserRequestDTO userRequestDTO = new CreateUserRequestDTO(userName, phoneNo, emailId, password);
        CreateUserResponseDTO userResponseDTO = userController.createUser(userRequestDTO);

        //Displaying User Details to the Client
        System.out.println("User Created Successfully with the following details");
        System.out.println("User Id: "+userResponseDTO.getUserProfile().getId());
        System.out.println("User Name: "+userResponseDTO.getUserProfile().getUserName());
        System.out.println("User Phone No: "+userResponseDTO.getUserProfile().getPhoneNo());
        System.out.println("User Email Id: "+userResponseDTO.getUserProfile().getEmailId());
    }

    @Override
    public List<String> getCommandName() {
        return List.of("create_user");
    }
}
