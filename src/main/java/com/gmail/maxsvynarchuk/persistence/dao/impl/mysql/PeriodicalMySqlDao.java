package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.PeriodicalDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.MapperFactory;
import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;
import com.gmail.maxsvynarchuk.util.ResourceManager;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PeriodicalMySqlDao implements PeriodicalDao {
    private final static String SELECT_ALL =
            ResourceManager.QUERIES.getProperty("periodical.select.all");
    private final static String INSERT =
            ResourceManager.QUERIES.getProperty("periodical.insert");
    private final static String UPDATE =
            ResourceManager.QUERIES.getProperty("periodical.update");
    private final static String DELETE =
            ResourceManager.QUERIES.getProperty("periodical.delete");
    private final static String COUNT =
            ResourceManager.QUERIES.getProperty("periodical.count");
    private final static String WHERE_ID =
            ResourceManager.QUERIES.getProperty("periodical.where.id");
    private final static String WHERE_STATUS =
            ResourceManager.QUERIES.getProperty("periodical.where.status");
    private final static String ORDER_BY_STATUS_AND_ID =
            ResourceManager.QUERIES.getProperty("periodical.select.order");

    private final UtilMySqlDao<Periodical> utilMySqlDao;

    public PeriodicalMySqlDao() {
        this(MapperFactory.getPeriodicalMapper());
    }

    public PeriodicalMySqlDao(EntityMapper<Periodical> mapper) {
        this(new UtilMySqlDao<>(mapper));
    }

    public PeriodicalMySqlDao(UtilMySqlDao<Periodical> utilMySqlDao) {
        this.utilMySqlDao = utilMySqlDao;
    }

    @Override
    public Optional<Periodical> findOne(Long id) {
        return utilMySqlDao.findOne(SELECT_ALL + WHERE_ID, id);
    }

    @Override
    public List<Periodical> findAll() {
        return utilMySqlDao.findAll(SELECT_ALL);
    }

    @Override
    public List<Periodical> findAll(long skip, long limit) {
        return utilMySqlDao.findAll(SELECT_ALL + ORDER_BY_STATUS_AND_ID + UtilMySqlDao.LIMIT, skip, limit);
    }

    @Override
    public List<Periodical> findAllPeriodicalsByStatus(PeriodicalStatus status, long skip, long limit) {
        return utilMySqlDao.findAll(SELECT_ALL + WHERE_STATUS + ORDER_BY_STATUS_AND_ID + UtilMySqlDao.LIMIT,
                status.toString(), skip, limit);
    }

    @Override
    public Periodical insert(Periodical obj) {
        Objects.requireNonNull(obj);

        Long id = utilMySqlDao.executeInsertWithGeneratedPrimaryKey(
                INSERT,
                obj.getPublisher().getId(),
                obj.getFrequency().getId(),
                obj.getPeriodicalType().getId(),
                obj.getName(),
                obj.getPrice(),
                obj.getDescription());
        obj.setId(id);

        return obj;
    }

    @Override
    public void update(Periodical obj) {
        Objects.requireNonNull(obj);

        utilMySqlDao.executeUpdate(
                UPDATE + WHERE_ID,
                obj.getPublisher().getId(),
                obj.getFrequency().getId(),
                obj.getPeriodicalType().getId(),
                obj.getName(),
                obj.getStatus().toString(),
                obj.getPrice(),
                obj.getDescription(),
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
    public long getCountByStatus(PeriodicalStatus periodicalStatus) {
        return utilMySqlDao.getRowsCount(COUNT + WHERE_STATUS, periodicalStatus.toString());
    }
}
