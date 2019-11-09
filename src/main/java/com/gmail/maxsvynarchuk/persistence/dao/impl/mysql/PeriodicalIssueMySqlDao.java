package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.PeriodicalIssueDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.PeriodicalIssueMapper;
import com.gmail.maxsvynarchuk.persistence.entity.PeriodicalIssue;
import com.gmail.maxsvynarchuk.util.TimeConverter;
import com.gmail.maxsvynarchuk.util.ResourceManager;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PeriodicalIssueMySqlDao implements PeriodicalIssueDao {
    private final static String SELECT_ALL =
            ResourceManager.QUERIES.getProperty("periodical.issue.select.all");
    private final static String INSERT =
            ResourceManager.QUERIES.getProperty("periodical.issue.insert");
    private final static String UPDATE =
            ResourceManager.QUERIES.getProperty("periodical.issue.update");
    private final static String DELETE =
            ResourceManager.QUERIES.getProperty("periodical.issue.delete");
    private final static String COUNT =
            ResourceManager.QUERIES.getProperty("periodical.issue.count");
    private final static String WHERE_ID =
            ResourceManager.QUERIES.getProperty("periodical.issue.where.id");

    private final UtilMySqlDao<PeriodicalIssue> utilMySqlDao;

    public PeriodicalIssueMySqlDao() {
        this(new PeriodicalIssueMapper());
    }

    public PeriodicalIssueMySqlDao(EntityMapper<PeriodicalIssue> mapper) {
        this(new UtilMySqlDao<>(mapper));
    }

    public PeriodicalIssueMySqlDao(UtilMySqlDao<PeriodicalIssue> utilMySqlDao) {
        this.utilMySqlDao = utilMySqlDao;
    }

    @Override
    public Optional<PeriodicalIssue> findOne(Long id) {
        return utilMySqlDao.findOne(SELECT_ALL + WHERE_ID, id);
    }

    @Override
    public List<PeriodicalIssue> findAll() {
        return utilMySqlDao.findAll(SELECT_ALL);
    }

    @Override
    public List<PeriodicalIssue> findAll(long skip, long limit) {
        return utilMySqlDao.findAll(SELECT_ALL + UtilMySqlDao.LIMIT, skip, limit);
    }

    @Override
    public PeriodicalIssue insert(PeriodicalIssue obj) {
        Objects.requireNonNull(obj);

        Long id = utilMySqlDao.executeInsertWithGeneratedPrimaryKey(
                INSERT,
                obj.getPeriodical().getId(),
                obj.getName(),
                obj.getIssueNumber(),
                TimeConverter.formatDate(obj.getPublicationDate()),
                obj.getDescription());
        obj.setId(id);

        return obj;
    }

    @Override
    public void update(PeriodicalIssue obj) {
        Objects.requireNonNull(obj);

        utilMySqlDao.executeUpdate(
                UPDATE + WHERE_ID,
                obj.getPeriodical().getId(),
                obj.getName(),
                obj.getIssueNumber(),
                TimeConverter.formatDate(obj.getPublicationDate()),
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
}
