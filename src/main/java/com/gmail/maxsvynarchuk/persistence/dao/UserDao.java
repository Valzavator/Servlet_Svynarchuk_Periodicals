package com.gmail.maxsvynarchuk.persistence.dao;

import com.gmail.maxsvynarchuk.persistence.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User, Long> {
    Optional<User> findOneByEmail(String email);

    boolean existByEmail(String email);
}
