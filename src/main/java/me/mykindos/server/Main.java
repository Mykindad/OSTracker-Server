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
        System.out.println("Server started...");
        MySQLServer.getInstance().setMySQLCredentials("127.0.0.1:3306", "tom", "!A9516254");
        //MySQLServer.getInstance().setMySQLUserCredentials("myusername", "mypassword");
        server = new Server(PORT);

    }
}
