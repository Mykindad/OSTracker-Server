package me.mykindos.server.commands.commands;

import me.mykindos.server.commands.ICommand;
import me.mykindos.server.mysql.MySQLServer;

/**
 * Command that establishes the connection to the MySQL Server
 */
public class ConnectMySQLCommand implements ICommand {
    @Override
    public boolean doesMatch(String command) {
        return command.startsWith("ConnectMySQL");
    }

    @Override
    public void execute(String... args) {
        try {
            MySQLServer.getInstance().establishConnection(args[0], args[1], args[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
