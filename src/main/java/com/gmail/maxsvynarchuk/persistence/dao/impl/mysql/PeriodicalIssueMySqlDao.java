package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.PeriodicalIssueDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.MapperFactory;
import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.PeriodicalIssue;
import com.gmail.maxsvynarchuk.persistence.exception.DaoException;
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
    private final static String WHERE_PERIODICAL_ID =
            ResourceManager.QUERIES.getProperty("periodical.issue.where.periodical");
    private final static String WHERE_NUMBER_AND_PERIODICAL_ID =
            ResourceManager.QUERIES.getProperty("periodical.issue.where.number.and.periodical");
    private final static String ORDER_BY_PUBLICATION_DATE =
            ResourceManager.QUERIES.getProperty("periodical.issue.select.order");

    private final UtilMySqlDao<PeriodicalIssue> utilMySqlDao;

    public PeriodicalIssueMySqlDao() {
        this(MapperFactory.getPeriodicalIssueMapper());
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
        return utilMySqlDao.findAll(SELECT_ALL + ORDER_BY_PUBLICATION_DATE);
    }

    @Override
    public List<PeriodicalIssue> findAll(long skip, long limit) {
        if (skip < 0 || limit < 0) {
            throw new DaoException("Skip or limit params cannot be negative");
        }
        return utilMySqlDao.findAll(SELECT_ALL + ORDER_BY_PUBLICATION_DATE + UtilMySqlDao.LIMIT, skip, limit);
    }

    @Override
    public List<PeriodicalIssue> findByPeriodical(Periodical periodical) {
        if (Objects.isNull(periodical)) {
            throw new DaoException("Attempt to find issues by nullable periodical");
        }

        return utilMySqlDao.findAll(SELECT_ALL +  WHERE_PERIODICAL_ID + ORDER_BY_PUBLICATION_DATE, periodical.getId());
    }

    @Override
    public Optional<PeriodicalIssue> findOneByNumberAndPeriodical(String issueNumber, Periodical periodical) {
        if (Objects.isNull(periodical)) {
            throw new DaoException("Attempt to find issue by nullable periodical");
        }

        return utilMySqlDao.findOne(SELECT_ALL + WHERE_NUMBER_AND_PERIODICAL_ID,
                issueNumber, periodical.getId());
    }

    @Override
    public PeriodicalIssue insert(PeriodicalIssue obj) {
        if (Objects.isNull(obj)) {
            throw new DaoException("Attempt to insert nullable issue");
        }

        Long id = utilMySqlDao.executeInsertWithGeneratedPrimaryKey(
                INSERT,
                obj.getPeriodical().getId(),
                obj.getName(),
                obj.getIssueNumber(),
                obj.getPublicationDate(),
                obj.getDescription());
        obj.setId(id);

        return obj;
    }

    @Override
    public void update(PeriodicalIssue obj) {
        if (Objects.isNull(obj)) {
            throw new DaoException("Attempt to update nullable issue");
        }

        utilMySqlDao.executeUpdate(
                UPDATE + WHERE_ID,
                obj.getPeriodical().getId(),
                obj.getName(),
                obj.getIssueNumber(),
                obj.getPublicationDate(),
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
    public boolean existByNumberAndPeriodical(String issueNumber, Periodical periodical) {
        return findOneByNumberAndPeriodical(issueNumber, periodical).isPresent();
    }
}
