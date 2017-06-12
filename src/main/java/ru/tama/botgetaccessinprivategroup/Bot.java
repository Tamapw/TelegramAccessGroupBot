package ru.tama.botgetaccessinprivategroup;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ru.tama.botgetaccessinprivategroup.command.AbstractCommand;
import ru.tama.botgetaccessinprivategroup.command.CommandFactory;
import ru.tama.botgetaccessinprivategroup.entity.User;
import ru.tama.botgetaccessinprivategroup.service.ServiceFactory;
import ru.tama.botgetaccessinprivategroup.service.api.UserService;


/**
 * Class {@link Bot} extends {@link TelegramLongPollingBot} and realized {@code onUpdateReceived} method. In addition class set the behavior bots.
 */
public class Bot extends TelegramLongPollingBot {
    public final long ID_ADMIN = BuildVars.ADMIN_ID;

    public ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
    public UserService userService = serviceFactory.getUserServiceImpl();

    /**
     * {@inheritDoc}
     */
    public void onUpdateReceived(Update update) {
        Long idUserTelegram = null;
        String message = null;
        Long idChat = null;

        if (update.hasMessage()) {
            idUserTelegram = update.getMessage().getChat().getId();
            message = update.getMessage().getText();
            idChat = update.getMessage().getChatId();

            boolean isAdmin = idUserTelegram == BuildVars.ADMIN_ID;

            String[] messageCommand = message.split("::");


            AbstractCommand command = CommandFactory.getCommand(messageCommand[0], isAdmin);
            command.execute(update, this);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getBotUsername() {
        return BuildVars.USER_NAME_BOT;
    }

    /**
     * {@inheritDoc}
     */
    public String getBotToken() {
        return BuildVars.TOKEN_KEY;
    }
}
