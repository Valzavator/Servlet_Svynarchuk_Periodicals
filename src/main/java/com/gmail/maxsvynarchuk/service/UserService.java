package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.dao.UserDao;
import com.gmail.maxsvynarchuk.persistence.dao.factory.DaoFactory;
import com.gmail.maxsvynarchuk.persistence.entity.Role;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.util.PasswordManager;
import com.gmail.maxsvynarchuk.util.type.RoleType;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Intermediate layer between command layer and dao layer.
 * Implements operations of finding, creating, deleting entities.
 * Uses dao layer.
 *
 * @author Maksym Svynarchuk
 */
public class UserService {
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
        Optional<User> user = findByEmail(email);
        return user.filter(u -> PasswordManager.checkSecurePassword(
                password, u.getPassword()));
    }

    public Optional<User> findById(Long id) {
        return userDao.findOne(id);
    }

    public Optional<User> findByEmail(String email) {
        return userDao.findOneByEmail(email);
    }

    public boolean registerUser(User userToRegister) {
        Objects.requireNonNull(userToRegister);

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
