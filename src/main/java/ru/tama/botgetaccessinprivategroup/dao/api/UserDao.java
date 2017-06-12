package ru.tama.botgetaccessinprivategroup.dao.api;

import ru.tama.botgetaccessinprivategroup.entity.User;

import java.sql.SQLException;

/**
 * This user repository for work with database.
 *
 * @author tama
 */
public interface UserDao {
    /**
     * Check existing user with idUserTelegram in database.
     *
     * @param idUserTelegram is idUser in Telegram database. This field getting from update.
     * @return true, if User with isUserTelegram exists and false if not exists.
     * @throws SQLException is a checked SQL exception
     */
    Boolean isRegistredUser(long idUserTelegram) throws SQLException;

    /**
     * Return {@link User} by IdUserTelegram.
     *
     * @param idUserTelegram is idUser in Telegram database. This field getting from update.
     * @return User with idUserTelegram.
     * @throws SQLException is a checked SQL exception
     */
    User getUserByIdTelegram(long idUserTelegram) throws SQLException;

    /**
     * Create user in database by idUserTelegram.
     *
     * @param idUserTelegram is idUser in Telegram database. This field getting from update.
     * @throws SQLException is a checked SQL exception
     */
    void createUser(long idUserTelegram) throws SQLException;

    /**
     * Delete user from database by idUserTelegram.
     *
     * @param idUserTelegram is idUser in Telegram database. This field getting from update.
     * @throws SQLException is a checked SQL exception
     */
    void deleteUser(long idUserTelegram) throws SQLException;

    /**
     * Update name field in User entity.
     *
     * @param idUserTelegram is idUser in Telegram database. This field getting from update.
     * @param name is name user that we get from user in Telegram chat.
     * @throws SQLException is a checked SQL exception
     */
    void updateUsername(long idUserTelegram, String name) throws SQLException;

    /**
     * Update phone field im User entity.
     *
     * @param idUserTelegram is idUser in Telegram database. This field getting from update.
     * @param phone is phone user that we get from user in Telegram chat.
     * @throws SQLException is a checked SQL exception
     */
    void updatePhone(long idUserTelegram, String phone) throws SQLException;

    /**
     * Update date birthday field in User entity.
     *
     * @param idUserTelegram is idUser in Telegram database. This field getting from update.
     * @param dateBirthday is YearBirthday user that we get from user in Telegram chat.
     * @throws SQLException is a checked SQL exception
     */
    void updateDateBirthday(long idUserTelegram, String dateBirthday) throws SQLException;

    /**
     * Update access field in User entity.
     *
     * @param idUserTelegram is idUser in Telegram database. This field getting from update.
     * @param access is access for PrivateGroup
     * @throws SQLException is a checked SQL exception
     */
    void updateAccess(long idUserTelegram, int access) throws SQLException;
}
