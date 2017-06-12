package ru.tama.botgetaccessinprivategroup.service.api;

import ru.tama.botgetaccessinprivategroup.entity.User;

/**
 * This interface for business layer and work with UserEntity
 *
 * @author tama
 */
public interface UserService {
    /**
     * This method determines what information will be update. Username, phone or dateBirthday.
     *
     * @param message message was received from the update. This variable is message that User with idUser send to bot.
     * @param idUserTelegram is idUser in Telegram database. This field getting from update.
     * @return user that was updated
     */
    User updateUserGettingInformation(String message, long idUserTelegram);

    /**
     * Check existing user with idUserTelegram in database.
     *
     * @param idUserTelegram is idUser in Telegram database. This field getting from update.
     * @return true, if User with isUserTelegram exists and false if not exists.
     */
    boolean isRegistredUser(long idUserTelegram);

    /**
     * Create user in database with idUserTelegram field.
     *
     * @param idUserTelegram is idUser in Telegram database. This field getting from update.
     */
    void createUser(long idUserTelegram);

    /**
     * Delete user in database with idUserTelegram field.
     *
     * @param idUserTelegram is idUser in Telegram database. This field getting from update.
     */
    void deleteUser(long idUserTelegram);

    /**
     * Change access field on User entity.
     *
     * @param idUserTelegram is idUser in Telegram database. This field getting from update.
     * @param access is access for PrivateGroup
     */
    void setUserAccess(long idUserTelegram, int access);
}
