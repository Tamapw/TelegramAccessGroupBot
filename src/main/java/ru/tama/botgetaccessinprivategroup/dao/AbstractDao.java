package ru.tama.botgetaccessinprivategroup.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tama on 11.06.17.
 */
public abstract class AbstractDao {
    protected static Logger logger = LoggerFactory.getLogger(AbstractDao.class);
    protected static DaoFactory factory;

    static {
        factory = DaoFactory.getDaoFactory();
    }
}
