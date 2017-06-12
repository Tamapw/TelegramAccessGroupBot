package ru.tama.botgetaccessinprivategroup;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
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

            doBot(idUserTelegram, idChat, message);
        }
    }

    /**
     * Defines bot behavior.
     *  1. If idUser = Admin then message came from Admin and method doByIdAdmin defined bot behavior in this case.
     *  2. if command is start - we create user and we ask the username.
     *  3. Update user information and depending stateGettingInformation to ask specific question
     *          and report about specific information.
     *
     *
     * @param idUserTelegram this id was received from the update. This variable is idUser in Telegram database.
     * @param idChat this idChar was received from the update. This variable is idChat with idUser in Telegram database.
     * @param message message was received from the update. This variable is message that User with idUser send to bot.
     */
    private void doBot(long idUserTelegram, long idChat, String message) {
        if (idUserTelegram == ID_ADMIN) {
            doByIdAdmin(message);
            return;
        }
        if (message.split(" ")[0].equals("/start")) {
            startBotMessage(idUserTelegram, idChat);
            return;
        }

        User user = userService.updateUserGettingInformation(message, idUserTelegram);

        if (user == null) {
           sendMessageByTextAndIdChat(idChat, "Некорректное значение, повторите ввод данных.");
           return;
        }

        switch (user.getStateGettingInformation()) {
            case 2: {
                sendMessageByTextAndIdChat(idChat, "Введите номер телефона.\nПример: 8-777-666-55-44");
            } break;

            case 3: {
                sendMessageByTextAndIdChat(idChat, "Введите год вашего рождения.\nПример: 1995");
            } break;

            case 4: {
                switch (user.getAccessForGroup()) {
                    case 0: {
                        sendMessageByTextAndIdChat(ID_ADMIN,
                                String.format("UserId: %d\nUsername: %s\n" +
                                                "UserPhone: %s\nUserDate: %s",
                                        user.getIdUserTelegram(), user.getNameUser(),
                                        user.getPhoneUser(), user.getDateBirthdayUser()
                                ));
                        userService.setUserAccess(idUserTelegram, 1);
                        sendMessageByTextAndIdChat(idChat,
                                "Ваши данные подтверждаются администратором, подождите.\n" +
                                        "Если данные будут подтверждены, ссылка на группу будет выслана вам в этом диалоге"
                        );
                    }
                    break;

                    case 1: {
                        sendMessageByTextAndIdChat(idChat,
                                "Ваши данные подтверждаются администратором, подождите.\n" +
                                        "Если данные будут подтверждены, ссылка на группу будет выслана вам в этом диалоге"
                        );
                    }
                    break;

                    case 2: {
                        sendMessageByTextAndIdChat(idChat,
                                "Ссылка на группу: " + BuildVars.getUrlPrivateGroup()
                        );
                    }
                    break;

                    case 3: {
                        sendMessageByTextAndIdChat(idChat,
                                "Доступ к группе закрыт.");
                    }
                }
            } break;
        }
    }

    /**
     * Bot behavior when message get came from admin. We check the command and execute they.
     *  1. Command - "no". This command means that the user specified in the message is not allowed to the group.
     *  2. Command - "yes". This command means that the user specified in the message allowed to the group.
     *  3. Command - "group". This command set urlPrivateGroup.
     *
     * @param message message which bot came from Admin.
     */
    private void doByIdAdmin(String message) {
        String[] messageParametr = message.split("::");

        if (messageParametr.length == 1) {
            sendMessageByTextAndIdChat(ID_ADMIN, "Некорректная команда");
            return;
        }

        if (messageParametr[0].equals("no")) {
            long idUserTelegram = Long.parseLong(messageParametr[1]);
            userService.setUserAccess(idUserTelegram, 3);
            sendMessageByTextAndIdChat(idUserTelegram, "Доступ к группе не выдан.");
            sendMessageByTextAndIdChat(ID_ADMIN, "Пользователь уведомлён.");
            return;
        }

        if (messageParametr[0].equals("yes")) {
            long idUserTelegram = Long.parseLong(messageParametr[1]);
            userService.setUserAccess(idUserTelegram, 2);

            sendMessageByTextAndIdChat(idUserTelegram, "Ссылка на группу: " + BuildVars.getUrlPrivateGroup());
            sendMessageByTextAndIdChat(ID_ADMIN, "Пользователь уведомлён.");
            return;
        }

        if (messageParametr[0].equals("group")) {
            String urlPG = messageParametr[1];
            BuildVars.setUrlPrivateGroup(urlPG);

            sendMessageByTextAndIdChat(ID_ADMIN, "Группа обновлена");
            return;
        }

        sendMessageByTextAndIdChat(ID_ADMIN, "Некорректная команда");
    }

    /**
     * This bot behavior when bot get command - "/start".
     * It meant that we create or delete and create user and send message to user.
     *
     * @param idUserTelegram is idUserTelegram that be created in database and be send message in Telegram.
     * @param idChat is idChat where the message will be sent.
     */
    private void startBotMessage(long idUserTelegram, long idChat) {
        sendMessageByTextAndIdChat(idChat,
                "Добрый день.\nВведите, пожалуйста, ваше ФИО.\nК примеру, Иванов Артём Алексеевич");

        if (!userService.isRegistredUser(idUserTelegram)) {
            userService.createUser(idUserTelegram);

            return;
        }

        userService.deleteUser(idUserTelegram);
        userService.createUser(idUserTelegram);
    }

    /**
     *  That wrap on sendMessage by idChat in message. This sends a message in the specified chat.
     *
     * @param idChat is chat where the message will be sent.
     * @param message is message which will be sent.
     */
    private void sendMessageByTextAndIdChat(long idChat, String message) {
        SendMessage sendMessageField = new SendMessage()
                .setChatId(idChat)
                .setText(message);

        try {
            sendMessage(sendMessageField);
        } catch (TelegramApiException ex) {
            ex.printStackTrace();
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
