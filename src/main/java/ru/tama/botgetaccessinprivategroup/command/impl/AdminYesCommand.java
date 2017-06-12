package ru.tama.botgetaccessinprivategroup.command.impl;

import org.telegram.telegrambots.api.objects.Update;
import ru.tama.botgetaccessinprivategroup.Bot;
import ru.tama.botgetaccessinprivategroup.BuildVars;
import ru.tama.botgetaccessinprivategroup.command.AbstractCommand;
import ru.tama.botgetaccessinprivategroup.command.CommandFactory;

/**
 * Admin command "yes". It means that user allowed in private group and he get link in private group.
 *
 * @author tama
 */
public class AdminYesCommand extends AbstractCommand {

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
        userService.setUserAccess(idUserTelegram, 2);

        sendMessageByTextAndIdChat(idUserTelegram, "Ссылка на группу: " + BuildVars.getUrlPrivateGroup());
        sendMessageByTextAndIdChat(BuildVars.ADMIN_ID, "Пользователь уведомлён.");
    }
}
