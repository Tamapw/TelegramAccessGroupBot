package ru.tama.botgetaccessinprivategroup.command;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ru.tama.botgetaccessinprivategroup.Bot;
import ru.tama.botgetaccessinprivategroup.service.ServiceFactory;
import ru.tama.botgetaccessinprivategroup.service.api.UserService;

/**
 * Created by tama on 12.06.17.
 */
public abstract class AbstractCommand {
    public ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
    public UserService userService = serviceFactory.getUserServiceImpl();
    private Bot bot;

    public abstract void execute(Update update, Bot bot);

    /**
     *  That wrap on sendMessage by idChat in message. This sends a message in the specified chat.
     *
     * @param idChat is chat where the message will be sent.
     * @param message is message which will be sent.
     */
    protected void sendMessageByTextAndIdChat(long idChat, String message) {
        SendMessage sendMessageField = new SendMessage()
                .setChatId(idChat)
                .setText(message);
        try {
            bot.sendMessage(sendMessageField);
        } catch (TelegramApiException ex) {
            ex.printStackTrace();
        }
    }

    public Bot getBot() {
        return bot;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }
}
