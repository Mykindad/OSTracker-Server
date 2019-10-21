package me.mykindos.server.commands.commands.inserts;

import me.mykindos.server.commands.ICommand;
import me.mykindos.server.mysql.QueryFactory;

/**
 * Processes the AddScriptItem command when received from a client.
 * Inserts multiple sets per query to increase performance
 */
public class AddScriptItemCommand implements ICommand {

    @Override
    public boolean doesMatch(String command) {
        return command.startsWith("AddScriptItem");
    }

    /**
     * [0] Script name
     * [1] OSBot Username
     * [2] Item Entry (Args: Item Name, Amount, Status)
     * @param args Command arguments
     */
    @Override
    public void execute(String... args) {
        String[] itemEntries = args[2].split("!-!");
        if(itemEntries.length > 0) {
            String query = "INSERT INTO `osbot-" + args[0] + ".scriptitems` (user, item, amount, itemStatus) VALUES ";
            for(String s : itemEntries){
                String[] entryArgs = s.split(",");
                query += "((SELECT id from `osbot-" + args[0] + ".users` WHERE username = '" + args[1] + "'), " +
                        "(SELECT id from `osbot-" + args[0] + ".items` WHERE item = '" + entryArgs[0] + "'), " +
                        "" + entryArgs[1] + "," +
                        "(SELECT id from `osbot-" + args[0] + ".itemstatus` WHERE status = '" + entryArgs[2] + "')),";

            }

            query = query.substring(0, query.length() -1); // Remove the end comma
            query+= ";"; // Complete the query

            QueryFactory.runQuery(query);
        }
    }
}
