package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.SubscriptionDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.SubscriptionMapper;
import com.gmail.maxsvynarchuk.persistence.entity.Subscription;
import com.gmail.maxsvynarchuk.persistence.util.time.TimeConverter;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SubscriptionMySqlDao implements SubscriptionDao {
    private final static String SELECT_ALL =
            "SELECT * FROM subscriptions " +
                    "JOIN subscription_plans " +
                    "ON subscriptions.subscription_plan_id = subscription_plans.subscription_plan_id " +
                    "JOIN payments " +
                    "ON subscriptions.payment_id = payments.payment_id " +
                    "JOIN users " +
                    "ON subscriptions.user_id = users.user_id " +
                    "JOIN roles " +
                    "ON users.role_id = roles.role_id " +
                    "LEFT JOIN addresses " +
                    "ON users.address_id = addresses.address_id " +
                    "JOIN periodicals " +
                    "ON subscriptions.periodical_id = periodicals.periodical_id " +
                    "JOIN publishers " +
                    "ON periodicals.publisher_id = publishers.publisher_id " +
                    "JOIN frequencies " +
                    "ON periodicals.frequency_id = frequencies.frequency_id " +
                    "JOIN periodical_types " +
                    "ON periodicals.periodical_type_id = periodical_types.periodical_type_id ";

    private final static String INSERT =
            "INSERT INTO subscriptions " +
                    "(payment_id, user_id, periodical_id, " +
                    "subscription_plan_id, start_date, end_date) " +
                    "VALUES(?, ?, ?, ?, ?, ?) ";

    private final static String UPDATE =
            "UPDATE subscriptions SET " +
                    "payment_id = ?, user_id = ?, " +
                    "periodical_id = ?, subscription_plan_id = ?, " +
                    "start_date = ?, end_date = ? ";

    private final static String DELETE =
            "DELETE FROM subscriptions ";

    private final static String WHERE_ID =
            "WHERE subscription_id = ? ";


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
