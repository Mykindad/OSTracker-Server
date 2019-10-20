package me.mykindos.server.server.threads;

import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread {

    private final Socket client;

    /**
     * Create Thread for individual Socket
     * @param client The socket connection
     */
    public ClientThread(Socket client){
        this.client = client;
    }

    /**
     * Processes the request from the connected client
     */
    public void run() {

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
