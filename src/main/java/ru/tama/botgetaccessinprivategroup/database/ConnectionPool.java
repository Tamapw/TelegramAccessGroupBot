package ru.tama.botgetaccessinprivategroup.database;

import ru.tama.botgetaccessinprivategroup.BuildVars;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


/**
 * This class is for working with connections
 *
 * @author Turlygazy
 */
public class ConnectionPool {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

    //data for settings of connection
    private static final int CONNECTION_NUMBER = 10;
    private static final String JDBC_URL = BuildVars.LINK_TO_DB;
    private static final String DB_LOGIN = BuildVars.USERNAME_DB;
    private static final String DB_PASSWORD = BuildVars.PASSWORD_DB;

    private static BlockingQueue<Connection> connections = new LinkedBlockingQueue<Connection>();

    static {
      //  try {
      //      //loading of class
      //      Class.forName(BuildVars.DRIVER_DB);
      //      logger.info(" \"" + BuildVars.DRIVER_DB + "\" Loading.");
      //  } catch (ClassNotFoundException e) {
      //      logger.info("����� \""+ BuildVars.DRIVER_DB + "\" Exception.");
      //      throw new ConnectionPoolException(e);
      //  }
        Connection connection;
        try {
            //getting connection from DriverManager and filling BlockingQueue<Connection> connections
            for (int i = 0; i < CONNECTION_NUMBER; i++) {
                connection = DriverManager.getConnection(JDBC_URL, DB_LOGIN, DB_PASSWORD);
                connections.add(connection);
                logger.debug(connection + "added.");
            }
        } catch (SQLException e) {
            logger.info("SQLException.");
            throw new ConnectionPoolException(e);
        }

    }

    private ConnectionPool() {
    }

    static Connection connection;

    public synchronized static Connection getConnection() throws ConnectionPoolException {
        try {
            //try get connection with 5 seconds waiting //��������� ������������
            connection = connections.poll(5, TimeUnit.SECONDS);
            logger.debug(connection + " �������.");
        } catch (InterruptedException e) {
            logger.info("InterruptedException.");
            throw new ConnectionPoolException(e);
        }

        //the case when there is no free connection
        if (connection == null) throw new ConnectionPoolException("Connection is null");
        return connection;
    }

    /**
     * This method returns connection to BlockingQueue<Connection> connections
     *
     * @param connection for releasing
     * @throws ConnectionPoolException
     */
    public synchronized static void releaseConnection(Connection connection) throws ConnectionPoolException {
        try {
            //try to put connection with 5 seconds waiting
            connections.offer(connection, 5, TimeUnit.SECONDS);
            logger.debug(connection + " released.");
        } catch (InterruptedException e) {
            logger.info("InterruptedException.");
            throw new ConnectionPoolException("cannot put connection in pool");
        }
    }
}