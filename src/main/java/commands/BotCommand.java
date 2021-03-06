package commands;

import sx.blah.discord.handle.impl.events.guild
        .channel.message.MessageReceivedEvent;

/**
 * All Bot Commands should implement this
 */
public interface BotCommand {

    /**
     * The command name that is used to call the command
     * @return command name
     */
    String getCommandName();

    /**
     * Command execution is handled here
     * @param event passed event
     * @param args passed arguments
     */
    void runCommand(MessageReceivedEvent event, String args);
}
