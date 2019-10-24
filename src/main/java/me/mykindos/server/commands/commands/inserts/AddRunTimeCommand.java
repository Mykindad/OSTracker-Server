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
        if(args.length > 2) {
            String scriptName = args[0].toLowerCase();
            System.out.println(args[0].toLowerCase());
            String query = "INSERT INTO `osbot-" + scriptName + ".runtimes` (user, duration) VALUES ((SELECT id from `osbot-"
                    + scriptName + ".users` WHERE username = '" + args[1] + "'), '" + args[2] + "');";
            QueryFactory.getInstance().runQuery(query);
        }
    }
}
