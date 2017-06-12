package ru.tama.botgetaccessinprivategroup.dao.impl;

import ru.tama.botgetaccessinprivategroup.dao.AbstractDao;
import ru.tama.botgetaccessinprivategroup.dao.api.UserDao;
import ru.tama.botgetaccessinprivategroup.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This implementation {@link UserDao} interface.
 *
 * @author tama
 */
public class UserDaoImpl extends AbstractDao implements UserDao {
    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * {@inheritDoc}
     */
    public Boolean isRegistredUser(long idUserTelegram) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM User WHERE idUserTelegram = ?;"
        );
        preparedStatement.setLong(1, idUserTelegram);
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getResultSet();
        boolean result = resultSet.next();
        logger.info("isRegistredUser = " + String.valueOf(result));

        return result;
    }

    /**
     * {@inheritDoc}
     */
    public User getUserByIdTelegram(long idUserTelegram) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM User WHERE idUserTelegram = ?;"
        );
        preparedStatement.setLong(1, idUserTelegram);
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getResultSet();

         if (resultSet.next()) {
            User user = new User();
            user.setIdUser(resultSet.getLong(1));
            user.setIdUserTelegram(resultSet.getLong(2));
            user.setNameUser(resultSet.getString(3));
            user.setPhoneUser(resultSet.getString(4));
            user.setDateBirthdayUser(resultSet.getString(5));
            user.setAccessForGroup(resultSet.getInt(6));
            user.setStateGettingInformation(resultSet.getInt(7));

            return user;
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void createUser(long idUserTelegram) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO User (idUserTelegram, isAccess, stateGettingInformation)" +
                        "VALUES(?, ?, ?);"
        );
        preparedStatement.setLong(1, idUserTelegram);
        preparedStatement.setInt(2, 0);
        preparedStatement.setInt(3, 1);

        preparedStatement.execute();
        logger.info("Created User.");
    }

    /**
     * {@inheritDoc}
     */
    public void deleteUser(long idUserTelegram) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM User WHERE idUserTelegram = ?;"
        );
        preparedStatement.setLong(1, idUserTelegram);

        preparedStatement.execute();
        logger.info("Deleted User.");
    }

    /**
     * {@inheritDoc}
     */
    public void updateUsername(long idUserTelegram, String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE User SET nameUser = '" + name +
                        " ', stateGettingInformation = 2 WHERE idUserTelegram = " + String.valueOf(idUserTelegram) + ";"
        );

        preparedStatement.executeUpdate();
        logger.info("UpdatedUserName.");
    }

    /**
     * {@inheritDoc}
     */
    public void updatePhone(long idUserTelegram, String phone) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE User SET phoneUser = '" + phone +
                        "', stateGettingInformation = 3 WHERE idUserTelegram = " + String.valueOf(idUserTelegram) + ";"
        );

        preparedStatement.executeUpdate();
        logger.info("UpdatedUserPhone");
    }

    /**
     * {@inheritDoc}
     */
    public void updateDateBirthday(long idUserTelegram, String dateBirthday) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE User SET dateBthdUser = '" + dateBirthday +
                        "', stateGettingInformation = 4 WHERE idUserTelegram = " + String.valueOf(idUserTelegram) + ";"
        );

        preparedStatement.executeUpdate();
        logger.info("UpdatedUserDate ");
    }

    /**
     * {@inheritDoc}
     */
    public void updateAccess(long idUserTelegram, int access) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE User SET isAccess = " + String.valueOf(access) + ";"
        );

        preparedStatement.executeUpdate();
        logger.info("UpdatedAccessToTree");
    }
}

