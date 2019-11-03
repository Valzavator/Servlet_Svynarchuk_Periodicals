package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.PeriodicalDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.PeriodicalMapper;
import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
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
    private final static String WHERE_ID =
            ResourceManager.QUERIES.getProperty("periodical.where.id");

    private final UtilMySqlDao<Periodical> utilMySqlDao;

    public PeriodicalMySqlDao() {
        this(new PeriodicalMapper());
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
    public List<Periodical> findAll(int skip, int limit) {
        return utilMySqlDao.findAll(SELECT_ALL + UtilMySqlDao.LIMIT, skip, limit);
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
}
