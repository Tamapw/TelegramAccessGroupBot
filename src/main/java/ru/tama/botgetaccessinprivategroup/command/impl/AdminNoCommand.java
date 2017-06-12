package ru.tama.botgetaccessinprivategroup.command.impl;

import org.telegram.telegrambots.api.objects.Update;
import ru.tama.botgetaccessinprivategroup.Bot;
import ru.tama.botgetaccessinprivategroup.BuildVars;
import ru.tama.botgetaccessinprivategroup.command.AbstractCommand;
import ru.tama.botgetaccessinprivategroup.command.CommandFactory;

/**
 * Admin command "no". It means that user not allowed in private group.
 *
 * @author tama
 */
public class AdminNoCommand extends AbstractCommand{

    public void execute(Update update, Bot bot) {
        setBot(bot);

        String message = update.getMessage().getText();
        String[] messageParametr = message.split("::");

        if (messageParametr.length != 2) {
            CommandFactory commandFactory = CommandFactory.getCommandFactory();
            commandFactory.getAdminNotFoundCommand().execute(update, bot);
            return;
        }

        long idUserTelegram = Long.parseLong(messageParametr[1]);
        userService.setUserAccess(idUserTelegram, 3);
        sendMessageByTextAndIdChat(idUserTelegram, "Доступ к группе не выдан.");
        sendMessageByTextAndIdChat(BuildVars.ADMIN_ID, "Пользователь уведомлён.");
    }
}
