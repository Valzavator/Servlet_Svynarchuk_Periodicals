package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.PeriodicalTypeDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.PeriodicalTypeMapper;
import com.gmail.maxsvynarchuk.persistence.entity.PeriodicalType;
import com.gmail.maxsvynarchuk.util.ResourceManager;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PeriodicalTypeMySqlDao implements PeriodicalTypeDao {
    private final static String SELECT_ALL =
            ResourceManager.QUERIES.getProperty("periodical.type.select.all");
    private final static String INSERT =
            ResourceManager.QUERIES.getProperty("periodical.type.insert");
    private final static String UPDATE =
            ResourceManager.QUERIES.getProperty("periodical.type.update");
    private final static String DELETE =
            ResourceManager.QUERIES.getProperty("periodical.type.delete");
    private final static String COUNT =
            ResourceManager.QUERIES.getProperty("periodical.type.count");
    private final static String WHERE_ID =
            ResourceManager.QUERIES.getProperty("periodical.type.where.id");

    private final UtilMySqlDao<PeriodicalType> utilMySqlDao;

    public PeriodicalTypeMySqlDao() {
        this(new PeriodicalTypeMapper());
    }

    public PeriodicalTypeMySqlDao(EntityMapper<PeriodicalType> mapper) {
        this(new UtilMySqlDao<>(mapper));
    }

    public PeriodicalTypeMySqlDao(UtilMySqlDao<PeriodicalType> utilMySqlDao) {
        this.utilMySqlDao = utilMySqlDao;
    }

    @Override
    public Optional<PeriodicalType> findOne(Integer id) {
        return utilMySqlDao.findOne(SELECT_ALL + WHERE_ID, id);
    }

    @Override
    public List<PeriodicalType> findAll() {
        return utilMySqlDao.findAll(SELECT_ALL);
    }

    public List<PeriodicalType> findAll(int skip, int limit) {
        return utilMySqlDao.findAll(SELECT_ALL + UtilMySqlDao.LIMIT, skip, limit);
    }

    @Override
    public PeriodicalType insert(PeriodicalType obj) {
        Objects.requireNonNull(obj);

        Integer id = utilMySqlDao.executeInsertWithGeneratedPrimaryKey(
                INSERT,
                Integer.class,
                obj.getName(),
                obj.getDescription());
        obj.setId(id);

        return obj;
    }

    @Override
    public void update(PeriodicalType obj) {
        Objects.requireNonNull(obj);

        utilMySqlDao.executeUpdate(
                UPDATE + WHERE_ID,
                obj.getName(),
                obj.getDescription(),
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
