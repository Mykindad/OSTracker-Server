package me.mykindos.server.server.threads;

import me.mykindos.server.commands.CommandProcessor;
import me.mykindos.server.commands.exceptions.CommandNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Processes invidual client connections
 */
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
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                CommandProcessor.getInstance().processCommand(reader.readLine());
                reader.close();
                client.close();
            } catch (IOException | CommandNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    reader.close();
                }

                if(client != null && client.isConnected()){
                    client.close();
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }
}
