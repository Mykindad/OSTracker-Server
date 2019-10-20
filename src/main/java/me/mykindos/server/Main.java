package me.mykindos.server;

import me.mykindos.server.mysql.MySQLServer;
import me.mykindos.server.mysql.QueryFactory;
import me.mykindos.server.server.Server;

public class Main {

    private static Server server;
    private static MySQLServer connection;
    private static QueryFactory queryFactory;

    public static void main(String[] args){

        server = new Server(1337);
       // connection = new MySQLServer("127.0.0.1", "root", "12345678", "osFighter");
        queryFactory = new QueryFactory();
    }
}
