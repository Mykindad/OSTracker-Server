package me.mykindos.server.commands;

public interface ICommand {

    /**
     * If this implementation should process the command
     * @param command Command to test
     * @return True if this command is a match to the implementation
     */
    boolean doesMatch(String command);

    /**
     * Executes the command received from the client
     * @param args Command arguments
     */
    void execute(String... args);

}
