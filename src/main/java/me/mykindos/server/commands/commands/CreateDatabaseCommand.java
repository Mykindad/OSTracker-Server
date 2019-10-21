package me.mykindos.server.commands.commands;

import me.mykindos.server.commands.ICommand;
import me.mykindos.server.mysql.QueryFactory;

public class CreateDatabaseCommand implements ICommand {

    @Override
    public boolean doesMatch(String command) {
        return command.startsWith("CreateDatabase");
    }

    @Override
    public void execute(String... args) {
        QueryFactory.getInstance().runQuery("CREATE DATABASE IF NOT EXISTS `" + args[0] + "`;");
        QueryFactory.getInstance().createRepositories(args[0]);
    }
}
