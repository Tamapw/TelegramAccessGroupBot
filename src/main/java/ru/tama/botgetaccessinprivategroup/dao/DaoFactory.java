package ru.tama.botgetaccessinprivategroup.dao;

import ru.tama.botgetaccessinprivategroup.dao.api.UserDao;
import ru.tama.botgetaccessinprivategroup.dao.impl.UserDaoImpl;
import ru.tama.botgetaccessinprivategroup.database.ConnectionPool;

import java.sql.Connection;

/**
 * Created by tama on 11.06.17.
 */
public class DaoFactory {
    private static Connection connection = ConnectionPool.getConnection();
    private static DaoFactory daoFactory = new DaoFactory();

    public DaoFactory() {

    }

    public DaoFactory(Connection connection) {
        this.connection = connection;
    }

    public static DaoFactory getDaoFactory() {
        return daoFactory;
    }

    public void close() {
        ConnectionPool.releaseConnection(connection);
    }

    public UserDao getUserDao() {
        return new UserDaoImpl(connection);
    }
}
