package ru.tama.botgetaccessinprivategroup.database;

/**
 * Created by tama on 11.06.17.
 */

public class ConnectionPoolException extends RuntimeException {
    public ConnectionPoolException(Exception e) {
        super(e);
    }

    public ConnectionPoolException(String s) {
        super(s);
    }
}
