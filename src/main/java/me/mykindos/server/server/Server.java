package me.mykindos.server.server;

import me.mykindos.server.server.threads.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    private final int serverPort;
    private ServerSocket serverSocket;

    /**
     * Initialise the me.mykindos.server.server connection
     * @param port Port to be used for the me.mykindos.server.server
     */
    public Server(int port){
        serverPort = port;

        try{
            serverSocket = new ServerSocket(port);
            new ServerThread(serverSocket).run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialise(){

    }
}
