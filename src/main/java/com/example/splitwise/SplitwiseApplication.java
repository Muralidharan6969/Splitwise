package com.example.splitwise;

import com.example.splitwise.CommandDP.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SplitwiseApplication implements CommandLineRunner {
    private Scanner scn = new Scanner(System.in);
    private CommandRegistry commandRegistry;
    private CreateUserCommand createUserCommand;
    private CreateGroupCommand createGroupCommand;
    private CreateExpenseCommand createExpenseCommand;
    private SettleUpCommand settleUpCommand;
    private DeleteExpenseCommand deleteExpenseCommand;
    private PrintExpenseCommand printExpenseCommand;

    @Autowired
    public SplitwiseApplication(CommandRegistry commandRegistry,
                                CreateUserCommand createUserCommand,
                                CreateGroupCommand createGroupCommand,
                                CreateExpenseCommand createExpenseCommand,
                                SettleUpCommand settleUpCommand,
                                DeleteExpenseCommand deleteExpenseCommand,
                                PrintExpenseCommand printExpenseCommand) {
        this.commandRegistry = commandRegistry;
        this.createUserCommand = createUserCommand;
        this.createGroupCommand = createGroupCommand;
        this.createExpenseCommand = createExpenseCommand;
        this.settleUpCommand = settleUpCommand;
        this.deleteExpenseCommand = deleteExpenseCommand;
        this.printExpenseCommand = printExpenseCommand;
    }

    public static void main(String[] args) {

        SpringApplication.run(SplitwiseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        loadAllCommands();
        System.out.println("Welcome to Splitwise");
        System.out.println("Please find all available commands below");
        while(true){
            commandRegistry.printAllCommands();
            System.out.println("Enter the command");
            String input = scn.next();
            if(input.equals("exit")){
                break;
            }
            commandRegistry.execute(input);
        }
    }

    private void loadAllCommands() {
        commandRegistry.addCommand(createUserCommand);
        commandRegistry.addCommand(createGroupCommand);
        commandRegistry.addCommand(createExpenseCommand);
        commandRegistry.addCommand(settleUpCommand);
        commandRegistry.addCommand(deleteExpenseCommand);
        commandRegistry.addCommand(printExpenseCommand);
    }
}
