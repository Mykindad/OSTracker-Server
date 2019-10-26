package me.mykindos.server.commands.commands;

import me.mykindos.server.commands.ICommand;
import me.mykindos.server.mysql.MySQLServer;
import me.mykindos.server.mysql.QueryFactory;

/**
 * Command that gives a user full access to osbot related tables
 */
public class GrantPrivilegesCommand implements ICommand {

    @Override
    public boolean doesMatch(String command) {
        return command.startsWith("GrantPrivileges");
    }

    /**
     * Grant Privileges to the specified MySQL User
     * @param args Command arguments
     */
    @Override
    public void execute(String... args) {

        // Only grant privileges to Script related databases, anything else is not our business
        QueryFactory.getInstance().runQuery("GRANT ALL PRIVILEGES ON `osbot-%`.* to `" + MySQLServer.getInstance().getMysqlCreateUserUsername() + "`@`localhost`;");
    }
}
