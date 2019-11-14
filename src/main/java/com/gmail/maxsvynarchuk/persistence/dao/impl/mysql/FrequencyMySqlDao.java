package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.FrequencyDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.MapperFactory;
import com.gmail.maxsvynarchuk.persistence.entity.Frequency;
import com.gmail.maxsvynarchuk.util.ResourceManager;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FrequencyMySqlDao implements FrequencyDao {
    private final static String SELECT_ALL =
            ResourceManager.QUERIES.getProperty("frequency.select.all");
    private final static String INSERT =
            ResourceManager.QUERIES.getProperty("frequency.insert");
    private final static String UPDATE =
            ResourceManager.QUERIES.getProperty("frequency.update");
    private final static String DELETE =
            ResourceManager.QUERIES.getProperty("frequency.delete");
    private final static String COUNT =
            ResourceManager.QUERIES.getProperty("frequency.count");
    private final static String WHERE_ID =
            ResourceManager.QUERIES.getProperty("frequency.where.id");

    private final UtilMySqlDao<Frequency> utilMySqlDao;

    public FrequencyMySqlDao() {
        this(MapperFactory.getFrequencyMapper());
    }

    public FrequencyMySqlDao(EntityMapper<Frequency> mapper) {
        this(new UtilMySqlDao<>(mapper));
    }

    public FrequencyMySqlDao(UtilMySqlDao<Frequency> utilMySqlDao) {
        this.utilMySqlDao = utilMySqlDao;
    }

    @Override
    public Optional<Frequency> findOne(Integer id) {
        return utilMySqlDao.findOne(SELECT_ALL + WHERE_ID, id);
    }

    @Override
    public List<Frequency> findAll() {
        return utilMySqlDao.findAll(SELECT_ALL);
    }

    @Override
    public List<Frequency> findAll(long skip, long limit) {
        return utilMySqlDao.findAll(SELECT_ALL + UtilMySqlDao.LIMIT, skip, limit);
    }

    @Override
    public Frequency insert(Frequency obj) {
        Objects.requireNonNull(obj);

        Integer id = utilMySqlDao.executeInsertWithGeneratedPrimaryKey(
                INSERT,
                Integer.class,
                obj.getName(),
                obj.getMeaning());
        obj.setId(id);

        return obj;
    }

    @Override
    public void update(Frequency obj) {
        Objects.requireNonNull(obj);

        utilMySqlDao.executeUpdate(
                UPDATE + WHERE_ID,
                obj.getName(),
                obj.getMeaning(),
                obj.getId());
    }

    @Override
    public void delete(Integer id) {
        utilMySqlDao.executeUpdate(
                DELETE + WHERE_ID,
                id);
    }

    @Override
    public long getCount() {
        return utilMySqlDao.getRowsCount(COUNT);
    }
}
