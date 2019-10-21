package me.mykindos.server.commands.commands.inserts;

import me.mykindos.server.commands.ICommand;
import me.mykindos.server.mysql.QueryFactory;

import java.util.Arrays;

public class AddExperienceCommand implements ICommand {

    @Override
    public boolean doesMatch(String command) {
        return command.startsWith("AddExperience");
    }

    /**
     * [0] Script name
     * [1] OSBot Username
     * [2] EXP Entry (Args: Skill Name, Experience Gained)
     * @param args Command arguments
     */
    @Override
    public void execute(String... args) {
        String[] expEntries = args[2].split("!-!");
        if(expEntries.length > 0) {
            String query = "INSERT INTO `osbot-" + args[0] + ".runtime` (user, skill, exp) VALUES ";
            for(String s : expEntries){
                String[] entryArgs = s.split(",");
                query += "((SELECT id from `osbot-" + args[0] + ".users` WHERE username = '" + args[1] + "'), " +
                        "(SELECT id from `osbot-" + args[0] + ".skills` WHERE skill = '" + entryArgs[0] + "', '" + entryArgs[1] + "'),";
            }

            query = query.substring(0, query.length() -1); // Remove the end comma
            query+= ";"; // Complete the query

            QueryFactory.runQuery(query);
        }
    }
}
