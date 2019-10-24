package me.mykindos.server.commands.commands.inserts;

import me.mykindos.server.commands.ICommand;
import me.mykindos.server.mysql.QueryFactory;

public class AddItemCommand implements ICommand {
    @Override
    public boolean doesMatch(String command) {
        return command.startsWith("AddItem");
    }

    /**
     * [0] Script name
     * [1] Item Name
     * @param args Command arguments
     */
    @Override
    public void execute(String... args) {
        String scriptName = args[0].toLowerCase();
        String[] itemEntries = args[2].split("!-!");
        if (itemEntries.length > 0) {
            String query = "INSERT IGNORE INTO `osbot-" + scriptName + ".items` (itemName) VALUES ";
            for (String s : itemEntries) {
                query += "('" + s + "'),";

            }

            query = query.substring(0, query.length() - 1); // Remove the end comma
            query += ";"; // Complete the query

            QueryFactory.getInstance().runQuery(query);
        }
    }
}
