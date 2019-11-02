package me.mykindos.server;

import me.mykindos.server.mysql.MySQLServer;
import me.mykindos.server.server.Server;

public class Main {

    private static Server server;
    private static final int PORT = 1337;

    /**
     * Start the server
     * @param args arguments
     */
    public static void main(String[] args){

        MySQLServer.getInstance().setMySQLCredentials("127.0.0.1:3306", "root", "123");
        //MySQLServer.getInstance().setMySQLUserCredentials("test", "123");
        server = new Server(PORT);

    }
}
