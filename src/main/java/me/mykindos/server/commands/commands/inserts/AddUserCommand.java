package me.mykindos.server.commands.commands.inserts;

import me.mykindos.server.commands.ICommand;
import me.mykindos.server.mysql.QueryFactory;

/**
 * Command that adds an OSBot user to the users table
 */
public class AddUserCommand implements ICommand {
    @Override
    public boolean doesMatch(String command) {
        return command.startsWith("AddUser");
    }

    /**
     * [0] Script Name
     * [1] OSBot Username
     * @param args Command arguments
     */
    @Override
    public void execute(String... args) {
        QueryFactory.getInstance().runQuery("INSERT IGNORE INTO `osbot-"  + args[0].toLowerCase() + ".users` (username) VALUES ('" + args[1] + "');");
    }
}
