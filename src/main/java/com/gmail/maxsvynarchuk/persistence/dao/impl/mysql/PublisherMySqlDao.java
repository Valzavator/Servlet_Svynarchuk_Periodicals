package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.PublisherDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.PublisherMapper;
import com.gmail.maxsvynarchuk.persistence.entity.Publisher;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PublisherMySqlDao implements PublisherDao {
    private final static String SELECT_ALL =
            "SELECT * FROM publishers ";

    private final static String INSERT =
            "INSERT INTO publishers (publisher_name) VALUES(?) ";

    private final static String UPDATE =
            "UPDATE publishers SET publisher_name = ? ";

    private final static String DELETE =
            "DELETE FROM publishers ";

    private final static String WHERE_ID =
            "WHERE publisher_id = ? ";


    private final UtilMySqlDao<Publisher> utilMySqlDao;

    public PublisherMySqlDao() {
        this(new PublisherMapper());
    }

    public PublisherMySqlDao(EntityMapper<Publisher> mapper) {
        this(new UtilMySqlDao<>(mapper));
    }

    public PublisherMySqlDao(UtilMySqlDao<Publisher> utilMySqlDao) {
        this.utilMySqlDao = utilMySqlDao;
    }

    @Override
    public Optional<Publisher> findOne(Long id) {
        return utilMySqlDao.findOne(SELECT_ALL + WHERE_ID, id);
    }

    @Override
    public List<Publisher> findAll() {
        return utilMySqlDao.findAll(SELECT_ALL);
    }

    public List<Publisher> findAll(int skip, int limit) {
        return utilMySqlDao.findAll(SELECT_ALL + UtilMySqlDao.LIMIT, skip, limit);
    }

    @Override
    public Publisher insert(Publisher obj) {
        Objects.requireNonNull(obj);

        Long id = utilMySqlDao.executeInsertWithGeneratedPrimaryKey(
                INSERT,
                obj.getName());
        obj.setId(id);

        return obj;
    }

    @Override
    public void update(Publisher obj) {
        Objects.requireNonNull(obj);

        utilMySqlDao.executeUpdate(
                UPDATE + WHERE_ID,
                obj.getName(),
                obj.getId());
    }

    @Override
    public void delete(Long id) {
        utilMySqlDao.executeUpdate(
                DELETE + WHERE_ID,
                id);
    }
}
