package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.PaymentDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.PaymentMapper;
import com.gmail.maxsvynarchuk.persistence.entity.Payment;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PaymentMySqlDao implements PaymentDao {
    private final static String SELECT_ALL =
            "SELECT * FROM payments " +
                    "JOIN users " +
                    "ON payments.user_id = users.user_id " +
                    "JOIN roles " +
                    "ON users.role_id = roles.role_id " +
                    "LEFT JOIN addresses " +
                    "ON users.address_id = addresses.address_id ";

    private final static String INSERT =
            "INSERT INTO payments " +
                    "(user_id, total_price) " +
                    "VALUES(?, ?) ";

    private final static String UPDATE =
            "UPDATE payments SET " +
                    "user_id = ?, total_price = ? ";

    private final static String DELETE =
            "DELETE FROM payments ";

    private final static String WHERE_ID =
            "WHERE payment_id = ? ";


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
