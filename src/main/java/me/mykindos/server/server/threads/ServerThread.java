package me.mykindos.server.server.threads;

import com.mysql.cj.xdevapi.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Processes all incoming connections
 */
public class ServerThread extends Thread {

    private final ServerSocket serverSocket;

    public ServerThread(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Listens for new connections from clients and allocates them a new thread for processing.
     * Multiple client connections supported
     */
    public void run() {
        while (true) {
            try {
                if (serverSocket.isClosed()) {
                    return; // exit loop if server closes.
                }

                Socket receiver = null;

                try {
                    receiver = serverSocket.accept();
                    if (receiver.isConnected()) {
                        new ClientThread(receiver).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (receiver != null) {
                        receiver.close();
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
