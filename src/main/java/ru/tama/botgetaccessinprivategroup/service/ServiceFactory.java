package ru.tama.botgetaccessinprivategroup.service;

import org.glassfish.hk2.internal.ServiceLocatorFactoryImpl;
import ru.tama.botgetaccessinprivategroup.service.api.UserService;
import ru.tama.botgetaccessinprivategroup.service.impl.UserServiceImpl;

/**
 * Created by tama on 11.06.17.
 */
public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();

    public ServiceFactory() {

    }

    public static ServiceFactory getServiceFactory() {
        return serviceFactory;
    }

    public UserService getUserServiceImpl() {
        return new UserServiceImpl();
    }
}
