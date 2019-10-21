package me.mykindos.server.commands.commands.inserts;

import me.mykindos.server.commands.ICommand;
import me.mykindos.server.mysql.QueryFactory;

public class AddUserCommand implements ICommand {
    @Override
    public boolean doesMatch(String command) {
        return command.startsWith("AddUser");
    }

    @Override
    public void execute(String... args) {
        QueryFactory.getInstance().runQuery("INSERT IGNORE INTO `osbot-"  + args[0] + ".users` (username) VALUES ('" + args[1] + "');");
    }
}
