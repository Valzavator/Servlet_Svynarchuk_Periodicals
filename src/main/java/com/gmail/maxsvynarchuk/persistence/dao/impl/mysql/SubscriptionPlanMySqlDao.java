package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.SubscriptionPlanDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.SubscriptionPlanMapper;
import com.gmail.maxsvynarchuk.persistence.entity.SubscriptionPlan;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SubscriptionPlanMySqlDao implements SubscriptionPlanDao {
    private final static String SELECT_ALL =
            "SELECT * FROM subscription_plans ";

    private final static String INSERT =
            "INSERT INTO subscription_plans " +
                    "(plan_name, months_amount, rate, plan_description) " +
                    "VALUES(?, ?, ?, ?) ";

    private final static String UPDATE =
            "UPDATE subscription_plans SET " +
                    "plan_name = ?, months_amount = ?, " +
                    "rate = ?, plan_description = ? ";

    private final static String DELETE =
            "DELETE FROM subscription_plans ";

    private final static String WHERE_ID =
            "WHERE subscription_plan_id = ? ";


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
