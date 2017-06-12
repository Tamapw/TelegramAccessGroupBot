package ru.tama.botgetaccessinprivategroup.command.impl;

import org.telegram.telegrambots.api.objects.Update;
import ru.tama.botgetaccessinprivategroup.Bot;
import ru.tama.botgetaccessinprivategroup.BuildVars;
import ru.tama.botgetaccessinprivategroup.command.AbstractCommand;
import ru.tama.botgetaccessinprivategroup.entity.User;

/**
 * Update user information and depending stateGettingInformation to ask specific question
 *        and report about specific information.
 *
 * @author tama
 */
public class UserGettingInformationCommand extends AbstractCommand {

    public void execute(Update update, Bot bot) {
        setBot(bot);

        String message = update.getMessage().getText();
        long idUserTelegram = update.getMessage().getChat().getId();
        long idChat = update.getMessage().getChatId();

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
                        sendMessageByTextAndIdChat(BuildVars.ADMIN_ID,
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
}
