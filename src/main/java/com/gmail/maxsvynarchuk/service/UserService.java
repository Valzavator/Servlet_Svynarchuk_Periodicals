package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.dao.factory.DaoFactory;
import com.gmail.maxsvynarchuk.persistence.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Intermediate layer between command layer and dao layer.
 * Implements operations of finding, creating, deleting entities.
 * Uses dao layer.
 *
 * @author Maksym Svynarchuk
 */
public class UserService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private UserService() {
    }

    private static class Singleton {
        private final static UserService INSTANCE = new UserService();
    }

    public static UserService getInstance() {
        return Singleton.INSTANCE;
    }

    public Optional<User> findById(Long id) {
        return daoFactory.getUserDao().findOne(id);
    }

    public Optional<User> findByEmail(String email) {
        return daoFactory.getUserDao().findOneByEmail(email);
    }

    public List<User> findAllUsers() {
        return daoFactory.getUserDao().findAll();
    }

//    public User createUser(User user) {
//        Objects.requireNonNull(user);
//
//        if (user.getRole() == null) {
//            user.setDefaultRole();
//        }
//
//        String hash = PasswordStorage.getSecurePassword(
//                user.getPassword());
//        user.setPassword(hash);
//
//
//        try (DaoConnection connection = daoFactory.getConnection()) {
//            UserDao userDao = daoFactory.getUserDao(connection);
//            return userDao.insert(user);
//        }
//    }
//
//    public boolean isCredentialsValid(String email, String password) {
//        try (DaoConnection connection = daoFactory.getConnection()) {
//            UserDao userDao = daoFactory.getUserDao(connection);
//            Optional<User> user = userDao.findOneByEmail(email);
//
//            return user
//                    .filter(u -> PasswordStorage.checkSecurePassword(
//                            password, u.getPassword()))
//                    .isPresent();
//        }
//
//    }
//
//    public boolean isUserExists(User user) {
//
//    }

}
