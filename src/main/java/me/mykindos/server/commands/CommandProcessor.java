package me.mykindos.server.commands;

import me.mykindos.server.commands.commands.CreateDatabaseCommand;
import me.mykindos.server.commands.exceptions.CommandNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandProcessor {

    private List<ICommand> commands;

    public CommandProcessor() {
        commands = new ArrayList<>();

        commands.add(new CreateDatabaseCommand());
    }

    public void processCommand(String command) throws CommandNotFoundException {
        Optional<ICommand> optional = commands.stream().filter(c -> c.doesMatch(command)).findFirst();
        if(optional.isPresent()){
            ICommand com = optional.get();
            String[] args = command.substring(command.indexOf(";;")).split("--");
            com.execute(args);
        }else{
            throw new CommandNotFoundException("Command not found for " + command);
        }
    }


}
