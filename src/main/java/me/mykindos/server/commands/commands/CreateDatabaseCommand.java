package me.mykindos.server.commands.commands;

import me.mykindos.server.commands.ICommand;
import me.mykindos.server.mysql.QueryFactory;

/**
 * Command that creates the database for each script
 */
public class CreateDatabaseCommand implements ICommand {

    @Override
    public boolean doesMatch(String command) {
        return command.startsWith("CreateDatabase");
    }

    @Override
    public void execute(String... args) {

        QueryFactory.getInstance().runQuery("CREATE DATABASE IF NOT EXISTS `" + args[0].toLowerCase() + "`;");
        QueryFactory.getInstance().runQuery("USE `" + args[0].toLowerCase() + "`");  // Does not like the create table queries if no database is selected.
        QueryFactory.getInstance().runQuery("SET sql_mode = ''");
        QueryFactory.getInstance().createRepositories(args[0].toLowerCase());
    }
}
