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
        String query = "INSERT IGNORE INTO `osbot-"  + args[0] + ".items` (itemName) VALUES ('" + args[1] + "');";
        QueryFactory.getInstance().runQuery(query);
    }
}
