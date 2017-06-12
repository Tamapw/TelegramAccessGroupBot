package ru.tama.botgetaccessinprivategroup.command.impl;

import org.telegram.telegrambots.api.objects.Update;
import ru.tama.botgetaccessinprivategroup.Bot;
import ru.tama.botgetaccessinprivategroup.BuildVars;
import ru.tama.botgetaccessinprivategroup.command.AbstractCommand;
import ru.tama.botgetaccessinprivategroup.command.CommandFactory;

/**
 * Admin command "group". It set urlPrivateGroup.
 *
 * @author tama.
 */
public class AdminSetGroupCommand extends AbstractCommand{

    public void execute(Update update, Bot bot) {
        setBot(bot);

        String message = update.getMessage().getText();
        String[] messageParametr = message.split("::");

        if (messageParametr.length != 2) {
            CommandFactory commandFactory = CommandFactory.getCommandFactory();
            commandFactory.getAdminNotFoundCommand().execute(update, bot);
            return;
        }

        String urlPG = messageParametr[1];
        BuildVars.setUrlPrivateGroup(urlPG);

        sendMessageByTextAndIdChat(BuildVars.ADMIN_ID, "Группа обновлена");
    }
}
