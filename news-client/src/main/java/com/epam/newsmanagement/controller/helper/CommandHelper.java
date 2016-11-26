package com.epam.newsmanagement.controller.helper;

import com.epam.newsmanagement.command.Command;
import com.epam.newsmanagement.command.impl.UnknownCommand;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Contains all commands and gives it's by name.
 */

@Component
public final class CommandHelper {

    /**
     * Map with all commands
     */
    private Map<String, Command> commands;

    public CommandHelper() {
        commands = XmlHelper.getInstance().parse();
    }

    /**
     * Returns command by the name.
     *
     * @param commandName
     * @return command
     */
    public Command getCommand(String commandName) {

        Command command = null;

        commandName = commandName.replace('-', '_').toUpperCase().trim();

        command = commands.get(commandName);

        if (command == null) {
            command = new UnknownCommand();
        }

        return command;
    }

}
