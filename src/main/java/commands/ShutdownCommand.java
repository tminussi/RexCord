package commands;

import sx.blah.discord.handle.impl.events.guild
        .channel.message.MessageReceivedEvent;
import rexcord.RexCord;

/**
 * Logs out and terminates application
 */
public class ShutdownCommand implements BotCommand {

    /**
     * Used to call this command via a message
     */
    private static final String COMMAND_NAME = "shutdown";
    /**
     * Command description
     */
    private static final String COMMAND_DESCRIPTION = "Shutdowns bot";

    /**
     * Returns command name
     * @return Command Name
     */
    @Override
    public final String getCommandName() {
        return COMMAND_NAME;
    }

    /**
     * Runs this command
     * @param event passed event
     */
    @Override
    public final void runCommand(MessageReceivedEvent event, String args) {
        RexCord.sendMessage(event.getChannel(),
                "Disconnecting! :wave:");
        RexCord.getClient().logout();
        System.exit(0);
    }

    @Override
    public final String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }
}
