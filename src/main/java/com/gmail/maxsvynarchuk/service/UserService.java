package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.dao.UserDao;
import com.gmail.maxsvynarchuk.persistence.dao.factory.DaoFactory;
import com.gmail.maxsvynarchuk.persistence.entity.Role;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.util.PasswordManager;
import com.gmail.maxsvynarchuk.util.type.RoleType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

/**
 * Intermediate layer between command layer and dao layer.
 * Service responsible for processing user-related operations
 *
 * @author Maksym Svynarchuk
 */
public class UserService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserService.class);
    private final UserDao userDao = DaoFactory.getInstance().getUserDao();
    private final Role defaultRole = RoleType.USER.getValue();

    private UserService() {
    }

    private static class Singleton {
        private final static UserService INSTANCE = new UserService();
    }

    public static UserService getInstance() {
        return Singleton.INSTANCE;
    }

    public Optional<User> signIn(String email, String password) {
        LOGGER.debug("Attempt to sign in");
        Optional<User> user = findUserByEmail(email);
        return user.filter(u -> PasswordManager.checkSecurePassword(
                password, u.getPassword()));
    }

    public Optional<User> findUserById(Long id) {
        LOGGER.debug("Attempt to find user by id");
        return userDao.findOne(id);
    }

    public Optional<User> findUserByEmail(String email) {
        LOGGER.debug("Attempt to find user by email");
        return userDao.findOneByEmail(email);
    }

    public boolean registerUser(User userToRegister) {
        LOGGER.debug("Attempt to register new user");
        if (userToRegister.getRole() == null) {
            userToRegister.setRole(defaultRole);
        }
        String hash = PasswordManager.hashPassword(
                userToRegister.getPassword());
        userToRegister.setPassword(hash);

        boolean userIsPresent = userDao.existByEmail(userToRegister.getEmail());
        if (!userIsPresent) {
            userDao.insert(userToRegister);
            return true;
        }
        return false;
    }
}
