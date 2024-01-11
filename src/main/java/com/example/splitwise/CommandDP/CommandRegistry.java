package com.example.splitwise.CommandDP;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class CommandRegistry {
    private List<Command> commandList;
    CommandRegistry(){
        this.commandList = new ArrayList<>();
    }
    public void addCommand(Command c){
        commandList.add(c);
    }
    public void removeCommand(Command c){
        commandList.remove(c);
    }
    public void execute(String input) throws Exception {
        for(Command cList : commandList){
            if(cList.matches(input)){
                cList.execute(input);
                break;
            }
        }
    }

    public void printAllCommands() {
        for(Command cList : commandList){
            for (String s : cList.getCommandName()){
                System.out.print(s+" ");
            }
        }
        System.out.println();
    }
}
