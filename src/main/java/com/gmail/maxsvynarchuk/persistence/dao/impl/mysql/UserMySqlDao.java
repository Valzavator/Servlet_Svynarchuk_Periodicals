package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.UserDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.UserMapper;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.persistence.util.time.TimeConverter;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserMySqlDao implements UserDao {
    private final static String SELECT_ALL =
            "SELECT * " +
                    "FROM users " +
                    "JOIN roles " +
                    "ON users.role_id = roles.role_id " +
                    "LEFT JOIN addresses " +
                    "ON users.address_id = addresses.address_id ";

    private final static String INSERT =
            "INSERT INTO users" +
                    "(role_id, address_id, first_name, last_name, " +
                    "email, password, date_of_birth, gender) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";

    private final static String UPDATE =
            "UPDATE users SET " +
                    "role_id = ?, address_id = ?, " +
                    "first_name = ?, last_name = ?, " +
                    "email = ?, password = ?, " +
                    "date_of_birth = ?, gender = ? ";

    private final static String DELETE =
            "DELETE FROM users ";

    private final static String WHERE_ID =
            "WHERE users.user_id = ? ";

//    private final static String WHERE_EMAIL =
//            "WHERE users.email = ? ";

    private final UtilMySqlDao<User> utilMySqlDao;

    public UserMySqlDao() {
        this(new UserMapper());
    }

    public UserMySqlDao(EntityMapper<User> mapper) {
        this(new UtilMySqlDao<>(mapper));
    }

    public UserMySqlDao(UtilMySqlDao<User> utilMySqlDao) {
        this.utilMySqlDao = utilMySqlDao;
    }

    @Override
    public Optional<User> findOne(Long id) {
        return utilMySqlDao.findOne(SELECT_ALL + WHERE_ID, id);
    }

//    @Override
//    public Optional<User> findOneByEmail(String email) {
//        return defaultDao.findOne(SELECT_ALL + WHERE_EMAIL, email);
//    }

    @Override
    public List<User> findAll() {
        return utilMySqlDao.findAll(SELECT_ALL);
    }

    public List<User> findAll(int skip, int limit) {
        return utilMySqlDao.findAll(SELECT_ALL + UtilMySqlDao.LIMIT, skip, limit);
    }

    @Override
    public User insert(User obj) {
        Objects.requireNonNull(obj);

        long id = utilMySqlDao.executeInsertWithGeneratedPrimaryKey(
                INSERT,
                obj.getRole().getId(),
                obj.getAddress().getId(),
                obj.getFirstName(),
                obj.getLastName(),
                obj.getEmail(),
                obj.getPassword(),
                TimeConverter.formatDate(obj.getDateOfBirth()),
                obj.getGender().toString());

        obj.setId(id);
        return obj;
    }

    @Override
    public void update(User obj) {
        Objects.requireNonNull(obj);

        utilMySqlDao.executeUpdate(
                UPDATE + WHERE_ID,
                obj.getRole().getId(),
                obj.getAddress().getId(),
                obj.getFirstName(),
                obj.getLastName(),
                obj.getEmail(),
                obj.getPassword(),
                TimeConverter.formatDate(obj.getDateOfBirth()),
                obj.getGender().toString(),
                obj.getId());
    }

    @Override
    public void delete(Long id) {
        utilMySqlDao.executeUpdate(
                DELETE + WHERE_ID,
                id);
    }

//    @Override
//    public boolean existByEmail(String email) {
//        return findOneByEmail(email).isPresent();
//    }
}
