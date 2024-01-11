package com.example.splitwise.CommandDP;

import com.example.splitwise.Controllers.ExpenseController;
import com.example.splitwise.DTO.ExpenseGroupRequestDTO;
import com.example.splitwise.DTO.ExpenseGroupResponseDTO;
import com.example.splitwise.DTO.ExpenseIndRequestDTO;
import com.example.splitwise.DTO.ExpenseIndResponseDTO;
import com.example.splitwise.Services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CreateExpenseCommand implements Command{
    private Scanner scn = new Scanner(System.in);
    private ExpenseController expenseController;

    public CreateExpenseCommand() {}

    @Autowired
    public CreateExpenseCommand(ExpenseController expenseController) {
        this.expenseController = expenseController;
    }

    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split("\\s+"));
        return words.get(0).equals("create_expense");
    }

    @Override
    public void execute(String input) throws Exception {
        System.out.println("Is this expense part of a group? (Y/N)");
        String isGroupExpense = scn.next();
        if (isGroupExpense.equals("Y")) {
            System.out.println("Specify the group id");
            Long groupId = scn.nextLong();
            ExpenseGroupRequestDTO expenseGroupRequestDTO = new ExpenseGroupRequestDTO(groupId);
            ExpenseGroupResponseDTO expenseGroupResponseDTO = expenseController.
                    createGroupExpense(expenseGroupRequestDTO);

            //Displaying Expense Details to the Client
            System.out.println("Expense Created Successfully with the following details");
            ExpenseService.printExpenseDetails(expenseGroupResponseDTO.getExpense());
        }
        else{
            System.out.println("Specify the user id of the user who created this expense");
            Long userIdExpense = scn.nextLong();
            ExpenseIndRequestDTO expenseIndRequestDTO = new ExpenseIndRequestDTO(userIdExpense);
            ExpenseIndResponseDTO expenseIndResponseDTO = expenseController.
                    createExpense(expenseIndRequestDTO);

            //Displaying Expense Details to the Client
            System.out.println("Expense Created Successfully with the following details");
            ExpenseService.printExpenseDetails(expenseIndResponseDTO.getExpense());
        }

    }

    @Override
    public List<String> getCommandName() {
        return List.of("create_expense");
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
