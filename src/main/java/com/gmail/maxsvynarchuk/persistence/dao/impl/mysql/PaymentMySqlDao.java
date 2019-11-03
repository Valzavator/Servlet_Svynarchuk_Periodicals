package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.PaymentDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.PaymentMapper;
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
    private final static String WHERE_ID =
            ResourceManager.QUERIES.getProperty("payment.where.id");

    private final UtilMySqlDao<Payment> utilMySqlDao;

    public PaymentMySqlDao() {
        this(new PaymentMapper());
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
        return utilMySqlDao.findAll(SELECT_ALL);
    }

    @Override
    public List<Payment> findAll(int skip, int limit) {
        return utilMySqlDao.findAll(SELECT_ALL + UtilMySqlDao.LIMIT, skip, limit);
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
}
