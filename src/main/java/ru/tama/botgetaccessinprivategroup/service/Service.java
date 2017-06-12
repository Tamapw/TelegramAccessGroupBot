package ru.tama.botgetaccessinprivategroup.service;

import ru.tama.botgetaccessinprivategroup.dao.DaoFactory;
import ru.tama.botgetaccessinprivategroup.dao.api.UserDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by tama on 11.06.17.
 */
public class Service {
    DaoFactory daoFactory = DaoFactory.getDaoFactory();
    protected UserDao userDao = daoFactory.getUserDao();

    protected static final Logger logger = LoggerFactory.getLogger(Service.class);
}
