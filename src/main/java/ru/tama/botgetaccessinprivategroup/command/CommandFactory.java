package ru.tama.botgetaccessinprivategroup.command;

import ru.tama.botgetaccessinprivategroup.command.impl.*;

/**
 * Created by tama on 12.06.17.
 */
public class CommandFactory {
    private static CommandFactory commandFactory = new CommandFactory();

    public static AbstractCommand getCommand(String command, boolean isAdmin) {
        if (isAdmin) {
            if (command.equals("yes") && isAdmin) {
                return new AdminYesCommand();
            }
            if (command.equals("no") && isAdmin) {
                return new AdminNoCommand();
            }
            if (command.equals("group") && isAdmin) {
                return new AdminSetGroupCommand();
            }

            return new AdminNotFoundCommand();
        }

        if (command.equals("/start") || command.equals("/start ")) {
            return new UserStartCommand();
        }

        return new UserGettingInformationCommand();

    }

    public static CommandFactory getCommandFactory() {
        return commandFactory;
    }

    public AbstractCommand getAdminNoCommand() {
        return new AdminNoCommand();
    }

    public AbstractCommand getAdminYesCommand() {
        return new AdminYesCommand();
    }

    public AbstractCommand getAdminNotFoundCommand() {
        return new AdminNotFoundCommand();
    }

    public AbstractCommand getAdminSetGroupCommand() {
        return new AdminSetGroupCommand();
    }

    public AbstractCommand getUserGettingInformationCommand() {
        return new UserGettingInformationCommand();
    }

    public AbstractCommand getUserStartCommand() {
        return new UserGettingInformationCommand();
    }
}
