package ru.tama.botgetaccessinprivategroup;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ru.tama.botgetaccessinprivategroup.util.FileWrapper;

/**
 * This class initializer TelegramAPI and register bots
 *
 * @author tama.
 */
public class Main {
    public static void main(String[] args) {
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new Bot());
        } catch (TelegramApiException ex) {
            ex.printStackTrace();
        }
    }
}
