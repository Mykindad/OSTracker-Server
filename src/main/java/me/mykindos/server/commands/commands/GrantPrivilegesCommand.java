package me.mykindos.server.commands.commands;

import me.mykindos.server.commands.ICommand;
import me.mykindos.server.mysql.QueryFactory;

public class GrantPrivilegesCommand implements ICommand {

    @Override
    public boolean doesMatch(String command) {
        return command.startsWith("GrantPrivileges");
    }

    @Override
    public void execute(String... args) {

        // Only grant privileges to Script related databases, anything else is not our business
        QueryFactory.getInstance().runQuery("GRANT ALL PRIVILEGES ON `osbot-%`.* to `" + args[0] + "`@`localhost`;");
    }
}
