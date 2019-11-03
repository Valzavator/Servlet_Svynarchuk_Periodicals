package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.SubscriptionPlanDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.SubscriptionPlanMapper;
import com.gmail.maxsvynarchuk.persistence.entity.SubscriptionPlan;
import com.gmail.maxsvynarchuk.util.ResourceManager;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SubscriptionPlanMySqlDao implements SubscriptionPlanDao {
    private final static String SELECT_ALL =
            ResourceManager.QUERIES.getProperty("subscription.plan.select.all");
    private final static String INSERT =
            ResourceManager.QUERIES.getProperty("subscription.plan.insert");
    private final static String UPDATE =
            ResourceManager.QUERIES.getProperty("subscription.plan.update");
    private final static String DELETE =
            ResourceManager.QUERIES.getProperty("subscription.plan.delete");
    private final static String WHERE_ID =
            ResourceManager.QUERIES.getProperty("subscription.plan.where.id");

    private final UtilMySqlDao<SubscriptionPlan> utilMySqlDao;

    public SubscriptionPlanMySqlDao() {
        this(new SubscriptionPlanMapper());
    }

    public SubscriptionPlanMySqlDao(EntityMapper<SubscriptionPlan> mapper) {
        this(new UtilMySqlDao<>(mapper));
    }

    public SubscriptionPlanMySqlDao(UtilMySqlDao<SubscriptionPlan> utilMySqlDao) {
        this.utilMySqlDao = utilMySqlDao;
    }

    @Override
    public Optional<SubscriptionPlan> findOne(Integer id) {
        return utilMySqlDao.findOne(SELECT_ALL + WHERE_ID, id);
    }

    @Override
    public List<SubscriptionPlan> findAll() {
        return utilMySqlDao.findAll(SELECT_ALL);
    }

    public List<SubscriptionPlan> findAll(int skip, int limit) {
        return utilMySqlDao.findAll(SELECT_ALL + UtilMySqlDao.LIMIT, skip, limit);
    }

    @Override
    public SubscriptionPlan insert(SubscriptionPlan obj) {
        Objects.requireNonNull(obj);

        Integer id = utilMySqlDao.executeInsertWithGeneratedPrimaryKey(
                INSERT,
                Integer.class,
                obj.getName(),
                obj.getMonthsAmount(),
                obj.getRate(),
                obj.getDescription());
        obj.setId(id);

        return obj;
    }

    @Override
    public void update(SubscriptionPlan obj) {
        Objects.requireNonNull(obj);

        utilMySqlDao.executeUpdate(
                UPDATE + WHERE_ID,
                obj.getName(),
                obj.getMonthsAmount(),
                obj.getRate(),
                obj.getDescription(),
                obj.getId());
    }

    @Override
    public void delete(Integer id) {
        utilMySqlDao.executeUpdate(
                DELETE + WHERE_ID,
                id);
    }
}
