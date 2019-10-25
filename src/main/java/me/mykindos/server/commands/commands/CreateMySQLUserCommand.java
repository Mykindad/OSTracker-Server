package me.mykindos.server.commands.commands;

import me.mykindos.server.commands.ICommand;
import me.mykindos.server.mysql.QueryFactory;

/**
 * Command that creates a MySQL user
 */
public class CreateMySQLUserCommand implements ICommand {

    @Override
    public boolean doesMatch(String command) {
        return command.startsWith("CreateUser");
    }

    @Override
    public void execute(String... args) {

        // MySQL 8 requires mysql_native_password to login our way
        QueryFactory.getInstance().runQuery("CREATE USER IF NOT EXISTS `" + args[0]
                + "`@`localhost` IDENTIFIED WITH mysql_native_password BY '" + args[1] + "';");
    }
}
