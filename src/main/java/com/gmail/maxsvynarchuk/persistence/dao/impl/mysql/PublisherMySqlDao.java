package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.PublisherDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.MapperFactory;
import com.gmail.maxsvynarchuk.persistence.entity.Publisher;
import com.gmail.maxsvynarchuk.util.ResourceManager;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PublisherMySqlDao implements PublisherDao {
    private final static String SELECT_ALL =
            ResourceManager.QUERIES.getProperty("publisher.select.all");
    private final static String INSERT =
            ResourceManager.QUERIES.getProperty("publisher.insert");
    private final static String UPDATE =
            ResourceManager.QUERIES.getProperty("publisher.update");
    private final static String DELETE =
            ResourceManager.QUERIES.getProperty("publisher.delete");
    private final static String COUNT =
            ResourceManager.QUERIES.getProperty("publisher.count");
    private final static String WHERE_ID =
            ResourceManager.QUERIES.getProperty("publisher.where.id");

    private final UtilMySqlDao<Publisher> utilMySqlDao;

    public PublisherMySqlDao() {
        this(MapperFactory.getPublisherMapper());
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

    public List<Publisher> findAll(long skip, long limit) {
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

    @Override
    public long getCount() {
        return utilMySqlDao.getRowsCount(COUNT);
    }
}
