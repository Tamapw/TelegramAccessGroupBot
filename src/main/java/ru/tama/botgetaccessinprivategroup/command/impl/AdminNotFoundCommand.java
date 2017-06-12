package ru.tama.botgetaccessinprivategroup.command.impl;

import org.telegram.telegrambots.api.objects.Update;
import ru.tama.botgetaccessinprivategroup.Bot;
import ru.tama.botgetaccessinprivategroup.BuildVars;
import ru.tama.botgetaccessinprivategroup.command.AbstractCommand;

/**
 * NotFoundCommand send Admin message about it.
 *
 * @author tama
 */
public class AdminNotFoundCommand extends AbstractCommand{

    public void execute(Update update, Bot bot) {
        setBot(bot);

        sendMessageByTextAndIdChat(BuildVars.ADMIN_ID, "Некорректная команда.");
    }
}
