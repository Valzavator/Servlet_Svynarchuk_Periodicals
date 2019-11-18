package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.PaymentDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.MapperFactory;
import com.gmail.maxsvynarchuk.persistence.entity.Payment;
import com.gmail.maxsvynarchuk.util.ResourceManager;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PaymentMySqlDao implements PaymentDao {
    private final static String SELECT_ALL =
            ResourceManager.QUERIES.getProperty("payment.select.all");
    private final static String INSERT =
            ResourceManager.QUERIES.getProperty("payment.insert");
    private final static String UPDATE =
            ResourceManager.QUERIES.getProperty("payment.update");
    private final static String DELETE =
            ResourceManager.QUERIES.getProperty("payment.delete");
    private final static String COUNT =
            ResourceManager.QUERIES.getProperty("payment.count");
    private final static String WHERE_ID =
            ResourceManager.QUERIES.getProperty("payment.where.id");
    private final static String ORDER_BY_DATE =
            ResourceManager.QUERIES.getProperty("payment.select.order");

    private final UtilMySqlDao<Payment> utilMySqlDao;

    public PaymentMySqlDao() {
        this(MapperFactory.getPaymentMapper());
    }

    public PaymentMySqlDao(EntityMapper<Payment> mapper) {
        this(new UtilMySqlDao<>(mapper));
    }

    public PaymentMySqlDao(UtilMySqlDao<Payment> utilMySqlDao) {
        this.utilMySqlDao = utilMySqlDao;
    }

    @Override
    public Optional<Payment> findOne(Long id) {
        return utilMySqlDao.findOne(SELECT_ALL + WHERE_ID, id);
    }

    @Override
    public List<Payment> findAll() {
        return utilMySqlDao.findAll(SELECT_ALL + ORDER_BY_DATE);
    }

    @Override
    public List<Payment> findAll(long skip, long limit) {
        return utilMySqlDao.findAll(SELECT_ALL + ORDER_BY_DATE + UtilMySqlDao.LIMIT, skip, limit);
    }

    @Override
    public Payment insert(Payment obj) {
        Objects.requireNonNull(obj);

        Long id = utilMySqlDao.executeInsertWithGeneratedPrimaryKey(
                INSERT,
                obj.getUser().getId(),
                obj.getTotalPrice());
        obj.setId(id);

        return obj;
    }

    @Override
    public void update(Payment obj) {
        Objects.requireNonNull(obj);

        utilMySqlDao.executeUpdate(
                UPDATE + WHERE_ID,
                obj.getUser().getId(),
                obj.getTotalPrice(),
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
