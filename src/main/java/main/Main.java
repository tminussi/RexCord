package main;

import commands.CommandHandler;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;
import rexcord.RexCord;
import utils.ConfigReader;
import utils.PermissionReader;

import java.io.FileNotFoundException;

/**
 * Main Class
 */
public class Main {

    /**
     * Unexpected error
     */
    private static final String UNEXPECTED_ERROR =
            "Unexpected error while loading RexCord."
                    + "\nDid you insert a valid token?";

    /**
     * Default Error Message
     */
    private static final String ERROR_MESSAGE
            = "RexCord: Unable to start RexCord!";

    /**
     * RexCord terminating message
     */
    private static final String TERMINATING_MESSAGE
            = "RexCord: Terminating RexCord...";

    /**
     * Indicates that this class should not be instantiated
     */
    private Main() {

    }

    /**
     * Main method
     * @param args passed arguments
     */
    public static void main(String[] args) {

        // Sets start time for uptime command
        RexCord.setStartTime(System.currentTimeMillis());

        try {
            // Reads config file
            new ConfigReader();

            // Reads permissions
            new PermissionReader().readFile();

            startClient();
        } catch (FileNotFoundException e) { // Config File doesnt exist
            System.out.println(ERROR_MESSAGE);
            System.out.println(e.getMessage());
            System.out.println(TERMINATING_MESSAGE);

        } catch (ArrayIndexOutOfBoundsException e1) { // Bad config
            System.out.println(ERROR_MESSAGE);
            System.out.println(e1.getMessage());
            System.out.println(TERMINATING_MESSAGE);

        } catch (DiscordException e2) { // Invalid token
            System.out.println(ERROR_MESSAGE);
            System.out.println(UNEXPECTED_ERROR);
            System.out.println(TERMINATING_MESSAGE);
        }

    }

    /**
     * Starts Bot Client
     */
    private static void startClient() {
        // Creates a new Client
        IDiscordClient client = RexCord.createDiscordClient();

        // Registers a new listener
        client.getDispatcher().registerListener(new CommandHandler());

        // Logs in
        client.login();
    }
}
