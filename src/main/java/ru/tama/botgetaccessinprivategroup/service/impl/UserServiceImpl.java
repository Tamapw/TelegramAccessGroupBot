package ru.tama.botgetaccessinprivategroup.service.impl;

import ru.tama.botgetaccessinprivategroup.entity.User;
import ru.tama.botgetaccessinprivategroup.service.Service;
import ru.tama.botgetaccessinprivategroup.service.api.UserService;

import java.sql.SQLException;

/**
 * This implementation {@link UserService} interface.
 *
 * @author tama.
 */
public class UserServiceImpl extends Service implements ru.tama.botgetaccessinprivategroup.service.api.UserService {

    /**
     * {@inheritDoc}
     */
    public User updateUserGettingInformation(String message, long idUserTelegram) {
        User user = null;

        try {
             user = userDao.getUserByIdTelegram(idUserTelegram);
             if (user == null) {
                 logger.info("user undefined USerServiceImpl:17");
                 return null;
             }


            switch (user.getStateGettingInformation()) {
                case 1: {
                    logger.info("Update username");
                    userDao.updateUsername(idUserTelegram, message);
                } break;
                case 2: {
                    logger.info("Update phone");
                    userDao.updatePhone(idUserTelegram, message);
                } break;
                case 3: {
                    logger.info("Update Date birthday");
                    userDao.updateDateBirthday(idUserTelegram, message);
                } break;
                default: {
                    logger.info("stateGettingInformation undefined value");
                } break;
            }

            user = userDao.getUserByIdTelegram(idUserTelegram);
        } catch (SQLException ex) {
            logger.info("updateInformationException - " + ex);
            ex.printStackTrace();
        }
        return user;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isRegistredUser(long idUserTelegram) {
        try {
            return userDao.isRegistredUser(idUserTelegram);
        } catch (SQLException ex) {
            logger.info("isRegistredUserException - " + ex);
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    public void createUser(long idUserTelegram) {
        try {
            userDao.createUser(idUserTelegram);
        } catch (SQLException ex) {
            logger.info("CreateUserException - " + ex);
            ex.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void deleteUser(long idUserTelegram) {
        try {
            userDao.deleteUser(idUserTelegram);
        } catch (SQLException ex) {
            logger.info("DeleteUser exception - " + ex);
            ex.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setUserAccess(long idUserTelegram, int access) {
        try {
            userDao.updateAccess(idUserTelegram, access);
        } catch (SQLException ex) {
            logger.info("updateAccessToTree - " + ex);
            ex.printStackTrace();
        }
    }
}
