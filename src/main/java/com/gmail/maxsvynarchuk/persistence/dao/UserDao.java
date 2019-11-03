package com.gmail.maxsvynarchuk.persistence.dao;

import com.gmail.maxsvynarchuk.persistence.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User, Long> {
    public List<User> findAll(int skip, int limit);
}
