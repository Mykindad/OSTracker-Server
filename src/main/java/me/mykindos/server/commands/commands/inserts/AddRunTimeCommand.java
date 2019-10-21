package me.mykindos.server.commands.commands.inserts;

import me.mykindos.server.commands.ICommand;
import me.mykindos.server.mysql.QueryFactory;

public class AddRunTimeCommand implements ICommand {
    @Override
    public boolean doesMatch(String command) {
        return command.startsWith("AddRunTime");
    }

    /**
     * [0] Script name
     * [1] OSBot Username
     * [2] Run Duration
     * @param args Command arguments
     */
    @Override
    public void execute(String... args) {
        String query = "INSERT INTO `osbot-"  + args[0] + ".runtime` (user, duration) VALUES ((SELECT id from `osbot-"
                + args[0] + ".users` WHERE username = '" + args[1] + "'), '" + args[2] + "');";
        QueryFactory.runQuery(query);
    }
}
