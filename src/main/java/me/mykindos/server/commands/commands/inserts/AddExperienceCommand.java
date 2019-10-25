package me.mykindos.server.commands.commands.inserts;

import me.mykindos.server.commands.ICommand;
import me.mykindos.server.mysql.QueryFactory;


/**
 * Command that adds experience data for a user to the experiencegained table
 */
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
        if(args.length > 2) {
            String scriptName = args[0].toLowerCase();
            String[] expEntries = args[2].split("!-!");
            if (expEntries.length > 0) {
                String query = "INSERT INTO `osbot-" + scriptName + ".experiencegained` (user, skill, exp) VALUES ";
                for (String s : expEntries) {
                    String[] entryArgs = s.split(",");
                    query += "((SELECT id from `osbot-" + scriptName + ".users` WHERE username = '" + args[1] + "'), " +
                            "(SELECT id from `osbot-" + scriptName + ".skills` WHERE skillName = '" + entryArgs[0] + "'), '" + entryArgs[1] + "'),";
                }

                query = query.substring(0, query.length() - 1); // Remove the end comma
                query += ";"; // Complete the query

                QueryFactory.getInstance().runQuery(query);
            }
        }
    }
}
