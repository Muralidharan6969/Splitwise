package com.example.splitwise.Controllers;

import com.example.splitwise.DTO.ExpenseGroupRequestDTO;
import com.example.splitwise.DTO.ExpenseGroupResponseDTO;
import com.example.splitwise.DTO.ExpenseIndRequestDTO;
import com.example.splitwise.DTO.ExpenseIndResponseDTO;
import com.example.splitwise.Models.ENUMS.ExpenseType;
import com.example.splitwise.Models.Expense;
import com.example.splitwise.Services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class ExpenseController {
    private ExpenseService expenseService;
    private Scanner scn = new Scanner(System.in);

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public ExpenseIndResponseDTO createExpense(ExpenseIndRequestDTO expenseIndRequestDTO) throws Exception {
        System.out.println("Specify the Expense Description");
        String expenseDescription = scn.next();
        System.out.println("Specify the Total Amount involved in this Expense");
        int totalAmount = scn.nextInt();
        Expense expense = expenseService.createExpense(expenseDescription, totalAmount, ExpenseType.REAL,
                expenseIndRequestDTO.getUserId());
        return new ExpenseIndResponseDTO(expense);
    }

    public ExpenseGroupResponseDTO createGroupExpense(ExpenseGroupRequestDTO expenseGroupRequestDTO) throws Exception {
        System.out.println("Specify the Expense Description");
        String expenseDescription = scn.next();
        System.out.println("Specify the Total Amount involved in this Expense");
        int totalAmount = scn.nextInt();
        System.out.println("Specify the user who created this Expense");
        Long userIdExpense = scn.nextLong();
        Expense expense = expenseService.createGroupExpense(expenseDescription, totalAmount, userIdExpense,
                expenseGroupRequestDTO.getGroupId());
        return new ExpenseGroupResponseDTO(expense);
    }

    public void deleteExpenseByDescription(String expenseDescription) {
        expenseService.deleteExpenseByDescription(expenseDescription);
    }

    public void deleteExpenseById(Long expenseId) {
        expenseService.deleteExpenseById(expenseId);
    }

    public void printExpenseById(Long expenseId) throws Exception {
        expenseService.printExpenseById(expenseId);
    }

    public void printExpenseByDescription(String expenseDescription) throws Exception {
        expenseService.printExpenseByDescription(expenseDescription);
    }
}
