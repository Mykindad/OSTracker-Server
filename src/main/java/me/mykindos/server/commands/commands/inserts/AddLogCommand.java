package me.mykindos.server.commands.commands.inserts;

import me.mykindos.server.commands.ICommand;
import me.mykindos.server.mysql.QueryFactory;


/**
 * Command that adds a log to the log table
 */
public class AddLogCommand implements ICommand {

    @Override
    public boolean doesMatch(String command) {
        return command.startsWith("AddLog");
    }

    /**
     * [0] Script name
     * [1] OSBot Username
     * [2] Log
     *
     * @param args Command arguments
     */
    @Override
    public void execute(String... args) {
        if (args.length > 4) {
            String scriptName = args[0].toLowerCase();

            String query = "INSERT INTO `osbot-" + scriptName + "`.`logs` (user, version, mirror, log) VALUES " +
                    "((SELECT id from `osbot-" + scriptName + "`.`users` WHERE username='" + args[1] + "')," +
                    " '" + args[2] + "'," +
                    Boolean.valueOf(args[3]) + "," +
                    " '" + args[4].replaceAll("`", "") + "');";
            QueryFactory.getInstance().runQuery(query);

        }
    }
}
