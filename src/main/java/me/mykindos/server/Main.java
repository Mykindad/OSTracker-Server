package me.mykindos.server;

import me.mykindos.server.commands.CommandProcessor;
import me.mykindos.server.server.Server;

public class Main {

    private static Server server;

    public static void main(String[] args){

        server = new Server(1337);
        new CommandProcessor();

    }
}
