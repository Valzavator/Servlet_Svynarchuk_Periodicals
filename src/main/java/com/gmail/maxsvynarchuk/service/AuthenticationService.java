package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.dao.factory.DaoFactory;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.util.PasswordManager;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class AuthenticationService {
    private static final Logger LOGGER = LogManager.getLogger(AuthenticationService.class);

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private AuthenticationService() {
    }

    private static class Singleton {
        private final static AuthenticationService INSTANCE = new AuthenticationService();
    }

    public static AuthenticationService getInstance() {
        return AuthenticationService.Singleton.INSTANCE;
    }

}
