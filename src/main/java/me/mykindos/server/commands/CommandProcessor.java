package me.mykindos.server.commands;

import me.mykindos.server.commands.commands.ConnectMySQLCommand;
import me.mykindos.server.commands.commands.CreateDatabaseCommand;
import me.mykindos.server.commands.commands.CreateMySQLUserCommand;
import me.mykindos.server.commands.commands.GrantPrivilegesCommand;
import me.mykindos.server.commands.commands.inserts.*;
import me.mykindos.server.commands.exceptions.CommandNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Handles the commands that are received from clients.
 * Commands are used to insert data into the MySQL Database
 */
public class CommandProcessor {

    private static List<ICommand> commands = new ArrayList<>();;
    private static CommandProcessor commandProcessor;

    private CommandProcessor() {
        commands.add(new CreateDatabaseCommand());
        commands.add(new ConnectMySQLCommand());
        commands.add(new CreateMySQLUserCommand());
        commands.add(new GrantPrivilegesCommand());
        commands.add(new AddExperienceCommand());
        commands.add(new AddItemCommand());
        commands.add(new AddRunTimeCommand());
        commands.add(new AddScriptItemCommand());
        commands.add(new AddUserCommand());
    }

    /**
     * Processes commands received from the clients
     * @param command Command received from the client socket
     * @throws CommandNotFoundException
     */
    public static void processCommand(String command) throws CommandNotFoundException {
        System.out.println(command);
        Optional<ICommand> optional = commands.stream().filter(c -> c.doesMatch(command)).findFirst();
        if(optional.isPresent()){
            ICommand com = optional.get();
            String[] args = command.substring(command.indexOf(";;") + 2).split("--");
            com.execute(args);
        }else{
            throw new CommandNotFoundException("Command not found for " + command);
        }
    }

    public static CommandProcessor getInstance(){
        if(commandProcessor == null){
            commandProcessor = new CommandProcessor();
        }

        return commandProcessor;
    }


}
