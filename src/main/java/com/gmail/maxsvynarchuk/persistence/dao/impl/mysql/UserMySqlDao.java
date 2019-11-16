package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.UserDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.MapperFactory;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.util.TimeConverter;
import com.gmail.maxsvynarchuk.util.ResourceManager;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserMySqlDao implements UserDao {
    private final static String SELECT_ALL =
            ResourceManager.QUERIES.getProperty("user.select.all");
    private final static String INSERT =
            ResourceManager.QUERIES.getProperty("user.insert");
    private final static String UPDATE =
            ResourceManager.QUERIES.getProperty("user.update");
    private final static String DELETE =
            ResourceManager.QUERIES.getProperty("user.delete");
    private final static String COUNT =
            ResourceManager.QUERIES.getProperty("user.count");
    private final static String WHERE_ID =
            ResourceManager.QUERIES.getProperty("user.where.id");
    private final static String WHERE_EMAIL =
            ResourceManager.QUERIES.getProperty("user.where.email");

    private final UtilMySqlDao<User> utilMySqlDao;

    public UserMySqlDao() {
        this(MapperFactory.getUserMapper());
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

    @Override
    public Optional<User> findOneByEmail(String email) {
        return utilMySqlDao.findOne(SELECT_ALL + WHERE_EMAIL, email);
    }

    @Override
    public List<User> findAll() {
        return utilMySqlDao.findAll(SELECT_ALL);
    }

    public List<User> findAll(long skip, long limit) {
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
                obj.getDateOfBirth(),
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
                obj.getDateOfBirth(),
                obj.getGender().toString(),
                obj.getId());
    }

    @Override
    public void delete(Long id) {
        utilMySqlDao.executeUpdate(
                DELETE + WHERE_ID,
                id);
    }

    @Override
    public long getCount() {
        return utilMySqlDao.getRowsCount(COUNT);
    }

    @Override
    public boolean existByEmail(String email) {
        return findOneByEmail(email).isPresent();
    }
}
