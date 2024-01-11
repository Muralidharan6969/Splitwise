package com.example.splitwise.CommandDP;

import com.example.splitwise.Controllers.ExpenseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class DeleteExpenseCommand implements Command{
    private Scanner scn = new Scanner(System.in);
    private ExpenseController expenseController;

    @Autowired
    public DeleteExpenseCommand(ExpenseController expenseController) {
        this.expenseController = expenseController;
    }

    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split("\\s+"));
        return words.get(0).equals("delete_expense");
    }

    @Override
    public void execute(String input) throws Exception {
        System.out.println("Do you want to DELETE expense by description or by id? (description/id)");
        String choice = scn.next();
        if(choice.equals("id")){
            System.out.println("Specify the expense id");
            Long expenseId = scn.nextLong();
            expenseController.deleteExpenseById(expenseId);
        }
        else{
            System.out.println("Specify the expense description");
            String expenseDescription = scn.next();
            expenseController.deleteExpenseByDescription(expenseDescription);
        }
    }

    @Override
    public List<String> getCommandName() {
        return List.of("delete_expense");
    }
}
