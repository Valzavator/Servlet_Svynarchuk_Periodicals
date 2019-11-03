package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.SubscriptionDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.SubscriptionMapper;
import com.gmail.maxsvynarchuk.persistence.entity.Subscription;
import com.gmail.maxsvynarchuk.persistence.util.time.TimeConverter;
import com.gmail.maxsvynarchuk.util.ResourceManager;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SubscriptionMySqlDao implements SubscriptionDao {
    private final static String SELECT_ALL =
            ResourceManager.QUERIES.getProperty("subscription.select.all");
    private final static String INSERT =
            ResourceManager.QUERIES.getProperty("subscription.insert");
    private final static String UPDATE =
            ResourceManager.QUERIES.getProperty("subscription.update");
    private final static String DELETE =
            ResourceManager.QUERIES.getProperty("subscription.delete");
    private final static String WHERE_ID =
            ResourceManager.QUERIES.getProperty("subscription.where.id");

    private final UtilMySqlDao<Subscription> utilMySqlDao;

    public SubscriptionMySqlDao() {
        this(new SubscriptionMapper());
    }

    public SubscriptionMySqlDao(EntityMapper<Subscription> mapper) {
        this(new UtilMySqlDao<>(mapper));
    }

    public SubscriptionMySqlDao(UtilMySqlDao<Subscription> utilMySqlDao) {
        this.utilMySqlDao = utilMySqlDao;
    }

    @Override
    public Optional<Subscription> findOne(Long id) {
        return utilMySqlDao.findOne(SELECT_ALL + WHERE_ID, id);
    }

    @Override
    public List<Subscription> findAll() {
        return utilMySqlDao.findAll(SELECT_ALL);
    }

    @Override
    public List<Subscription> findAll(int skip, int limit) {
        return utilMySqlDao.findAll(SELECT_ALL + UtilMySqlDao.LIMIT, skip, limit);
    }

    @Override
    public Subscription insert(Subscription obj) {
        Objects.requireNonNull(obj);

        Long id = utilMySqlDao.executeInsertWithGeneratedPrimaryKey(
                INSERT,
                obj.getPayment().getId(),
                obj.getUser().getId(),
                obj.getPeriodical().getId(),
                obj.getSubscriptionPlan().getId(),
                TimeConverter.formatDate(obj.getStartDate()),
                TimeConverter.formatDate(obj.getEndDate()));
        obj.setId(id);

        return obj;
    }

    @Override
    public void update(Subscription obj) {
        Objects.requireNonNull(obj);

        utilMySqlDao.executeUpdate(
                UPDATE + WHERE_ID,
                obj.getPayment().getId(),
                obj.getUser().getId(),
                obj.getPeriodical().getId(),
                obj.getSubscriptionPlan().getId(),
                TimeConverter.formatDate(obj.getStartDate()),
                TimeConverter.formatDate(obj.getEndDate()),
                obj.getId());
    }

    @Override
    public void delete(Long id) {
        utilMySqlDao.executeUpdate(
                DELETE + WHERE_ID,
                id);
    }
}
