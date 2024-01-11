package com.example.splitwise.CommandDP;

import com.example.splitwise.Controllers.SettleUpController;
import com.example.splitwise.DTO.SettleUpGroupRequestDTO;
import com.example.splitwise.DTO.SettleUpGroupResponseDTO;
import com.example.splitwise.DTO.SettleUpUserRequestDTO;
import com.example.splitwise.DTO.SettleUpUserResponseDTO;
import com.example.splitwise.Models.Expense;
import com.example.splitwise.Services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class SettleUpCommand implements Command{
    private Scanner scn = new Scanner(System.in);
    private SettleUpController settleUpController;

    public SettleUpCommand(){}

    @Autowired
    public SettleUpCommand(SettleUpController settleUpController) {
        this.settleUpController = settleUpController;
    }

    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split("\\s+"));
        return words.get(0).equals("settle_up_user") || words.get(0).equals("settle_up_group");
    }

    @Override
    public void execute(String input) throws Exception {
        List<String> words = List.of(input.split("\\s+"));
        if(words.get(0).equals("settle_up_user")){
            System.out.println("Specify the user id");
            Long userId = scn.nextLong();
            SettleUpUserRequestDTO userRequestDTO = new SettleUpUserRequestDTO(userId);
            SettleUpUserResponseDTO userResponseDTO = settleUpController.settleUpUser(userRequestDTO);
            System.out.println("Expense Suggestion for the user is: ");
            for(Expense e : userResponseDTO.getExpenseSuggestion()){
                ExpenseService.printExpenseDetails(e);
            }
        }
        else if(words.get(0).equals("settle_up_group")){
            System.out.println("Specify the group id");
            Long groupId = scn.nextLong();
            SettleUpGroupRequestDTO groupRequestDTO = new SettleUpGroupRequestDTO(groupId);
            SettleUpGroupResponseDTO groupResponseDTO = settleUpController.settleUpGroup(groupRequestDTO);
            for (Expense e : groupResponseDTO.getExpenseSuggestion()){
                ExpenseService.printExpenseDetails(e);
            }
        }
    }

    @Override
    public List<String> getCommandName() {
        return List.of("settle_up_user", "settle_up_group");
    }

    /*public void printExpenseDetails(Expense expense){
        System.out.println("Expense Id: " + expense.getId());
        System.out.println("Expense Description: " + expense.getExpenseDescription());
        System.out.println("Expense Total Amount: " + expense.getExpenseTotalAmount());
        System.out.println("Expense Created By: " + expense.getCreatedBy().getUserName());
        if(expense.getGroupSplitwise()!=null){
            System.out.println("Expense Group: " + expense.getGroupSplitwise().getGroupName());
        }
        else{
            System.out.println("Expense Group: " + "Individual Expense");
        }
        System.out.println("Users and Amounts involved in this expense: ");
        for(ExpenseUser eu : expense.getExpenseUsers()){
            if(eu.getExpenseUserType().equals(ExpenseUserType.PAID)){
                System.out.println(eu.getUserProfile().getUserName()+" paid "+eu.getAmountInvolved());
            }
            else{
                System.out.println(eu.getUserProfile().getUserName()+" owes "+eu.getAmountInvolved());
            }
        }
    }*/
}
