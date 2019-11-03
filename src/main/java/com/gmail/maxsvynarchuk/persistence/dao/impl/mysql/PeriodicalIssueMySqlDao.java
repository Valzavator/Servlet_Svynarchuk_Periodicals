package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.PeriodicalIssueDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.PeriodicalIssueMapper;
import com.gmail.maxsvynarchuk.persistence.entity.PeriodicalIssue;
import com.gmail.maxsvynarchuk.persistence.util.time.TimeConverter;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PeriodicalIssueMySqlDao implements PeriodicalIssueDao {
    private final static String SELECT_ALL =
            "SELECT * FROM periodical_issues " +
                    "JOIN periodicals " +
                    "ON periodical_issues.periodical_id = periodicals.periodical_id " +
                    "JOIN publishers " +
                    "ON periodicals.publisher_id = publishers.publisher_id " +
                    "JOIN frequencies " +
                    "ON periodicals.frequency_id = frequencies.frequency_id " +
                    "JOIN periodical_types " +
                    "ON periodicals.periodical_type_id = periodical_types.periodical_type_id ";

    private final static String INSERT =
            "INSERT INTO periodical_issues " +
                    "(periodical_id, issues_name, issue_no, " +
                    "publication_date, issues_description) " +
                    "VALUES(?, ?, ?, ?, ?) ";

    private final static String UPDATE =
            "UPDATE periodical_issues SET " +
                    "periodical_id = ?, issues_name = ?, issue_no = ?, " +
                    "publication_date = ?, issues_description = ? ";

    private final static String DELETE =
            "DELETE FROM periodical_issues ";

    private final static String WHERE_ID =
            "WHERE periodical_issues_id = ? ";


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
    public List<PeriodicalIssue> findAll(int skip, int limit) {
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
}
