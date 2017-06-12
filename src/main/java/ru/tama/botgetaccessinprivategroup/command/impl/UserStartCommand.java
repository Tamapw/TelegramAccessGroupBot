package ru.tama.botgetaccessinprivategroup.command.impl;

import org.telegram.telegrambots.api.objects.Update;
import ru.tama.botgetaccessinprivategroup.Bot;
import ru.tama.botgetaccessinprivategroup.command.AbstractCommand;

/**
 * This bot behavior when bot get command - "/start".
 * It meant that we create or delete and create user and send message to user.
 *
 * @author tama
 */
public class UserStartCommand extends AbstractCommand{

    public void execute(Update update, Bot bot) {
        setBot(bot);

        long idChat = update.getMessage().getChatId();
        long idUserTelegram = update.getMessage().getChat().getId();

        sendMessageByTextAndIdChat(idChat,
                "Добрый день.\nВведите, пожалуйста, ваше ФИО.\nК примеру, Иванов Артём Алексеевич");

        if (!userService.isRegistredUser(idUserTelegram)) {
            userService.createUser(idUserTelegram);

            return;
        }

        userService.deleteUser(idUserTelegram);
        userService.createUser(idUserTelegram);
    }
}
