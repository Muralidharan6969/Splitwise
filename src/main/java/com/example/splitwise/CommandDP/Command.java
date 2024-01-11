package com.example.splitwise.CommandDP;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Command {
    public boolean matches(String input);
    public void execute(String input) throws Exception;

    List<String> getCommandName();
}
