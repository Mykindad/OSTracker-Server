package me.mykindos.server.commands.exceptions;

/**
 * Exception for when a command received from a client does not exist
 */
public class CommandNotFoundException extends Exception {

    public CommandNotFoundException(String s) {
        super(s);
    }

}
