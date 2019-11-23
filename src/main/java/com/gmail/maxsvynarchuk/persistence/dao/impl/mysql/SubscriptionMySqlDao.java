package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.SubscriptionDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.MapperFactory;
import com.gmail.maxsvynarchuk.persistence.entity.Payment;
import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.Subscription;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.persistence.exception.DaoException;
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
    private final static String COUNT =
            ResourceManager.QUERIES.getProperty("subscription.count");
    private final static String IS_USER_ALREADY_SUBSCRIBED =
            ResourceManager.QUERIES.getProperty("subscription.is.user.already.subscribed");
    private final static String WHERE_ID =
            ResourceManager.QUERIES.getProperty("subscription.where.id");
    private final static String WHERE_PAYMENT_ID =
            ResourceManager.QUERIES.getProperty("subscription.where.payment");
    private final static String WHERE_ACTIVE_AND_USER_ID =
            ResourceManager.QUERIES.getProperty("subscription.where.active.and.user");
    private final static String WHERE_EXPIRED_AND_USER_ID =
            ResourceManager.QUERIES.getProperty("subscription.where.expired.and.user");
    private final static String ORDER_BY_END_DATE =
            ResourceManager.QUERIES.getProperty("subscription.select.order");

    private final UtilMySqlDao<Subscription> utilMySqlDao;

    public SubscriptionMySqlDao() {
        this(MapperFactory.getSubscriptionMapper());
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
    public List<Subscription> findAll(long skip, long limit) {
        if (skip < 0 || limit < 0) {
            throw new DaoException("Skip or limit params cannot be negative");
        }
        return utilMySqlDao.findAll(SELECT_ALL + UtilMySqlDao.LIMIT, skip, limit);
    }

    @Override
    public List<Subscription> findByUserAndStatus(User user, boolean isExpired, long skip, long limit) {
        if (skip < 0 || limit < 0) {
            throw new DaoException("Skip or limit params cannot be negative");
        }
        return isExpired
                ? utilMySqlDao.findAll(
                SELECT_ALL + WHERE_EXPIRED_AND_USER_ID + ORDER_BY_END_DATE + UtilMySqlDao.LIMIT,
                user.getId(), skip, limit)
                : utilMySqlDao.findAll(
                SELECT_ALL + WHERE_ACTIVE_AND_USER_ID + ORDER_BY_END_DATE + UtilMySqlDao.LIMIT,
                user.getId(), skip, limit);
    }

    @Override
    public List<Subscription> findByPayment(Payment payment) {
        return utilMySqlDao.findAll(SELECT_ALL + WHERE_PAYMENT_ID,
                payment.getId());
    }

    @Override
    public Subscription insert(Subscription obj) {
        if (Objects.isNull(obj)) {
            throw new DaoException("Attempt to insert nullable subscription");
        }

        Long id = utilMySqlDao.executeInsertWithGeneratedPrimaryKey(
                INSERT,
                obj.getPayment().getId(),
                obj.getUser().getId(),
                obj.getPeriodical().getId(),
                obj.getSubscriptionPlan().getId(),
                obj.getStartDate(),
                obj.getEndDate());
        obj.setId(id);

        return obj;
    }

    @Override
    public void update(Subscription obj) {
        if (Objects.isNull(obj)) {
            throw new DaoException("Attempt to update nullable subscription");
        }

        utilMySqlDao.executeUpdate(
                UPDATE + WHERE_ID,
                obj.getPayment().getId(),
                obj.getUser().getId(),
                obj.getPeriodical().getId(),
                obj.getSubscriptionPlan().getId(),
                obj.getStartDate(),
                obj.getEndDate(),
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
    public long getCountByUserAndStatus(User user, boolean isExpired) {
        return isExpired
                ? utilMySqlDao.getRowsCount(COUNT + WHERE_EXPIRED_AND_USER_ID, user.getId())
                : utilMySqlDao.getRowsCount(COUNT + WHERE_ACTIVE_AND_USER_ID, user.getId());
    }

    @Override
    public boolean isUserAlreadySubscribed(User user, Periodical periodical) {
        long count = utilMySqlDao.getRowsCount(IS_USER_ALREADY_SUBSCRIBED,
                user.getId(), periodical.getId());
        if (count > 1) {
            throw new DaoException("User cannot be subscribed twice to one edition!");
        }
        return count == 1;
    }
}
