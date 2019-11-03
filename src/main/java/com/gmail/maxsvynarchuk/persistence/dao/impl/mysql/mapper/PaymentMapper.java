package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.Payment;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.persistence.util.time.TimeConverter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMapper implements EntityMapper<Payment> {
    private static final String ID_FIELD = "payment_id";
    private static final String PAYMENT_DATE_FIELD = "payment_date";
    private static final String TOTAL_PRICE_FIELD = "total_price";

    private final EntityMapper<User> userMapper;

    public PaymentMapper() {
        this(new UserMapper());
    }

    public PaymentMapper(EntityMapper<User> userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Payment mapToObject(ResultSet resultSet, String tablePrefix) throws SQLException {
        User tempUser = userMapper.mapToObject(resultSet);

        return Payment.newBuilder()
                .setId(resultSet.getLong(
                        tablePrefix + ID_FIELD))
                .setUser(tempUser)
                .setPaymentDate(TimeConverter.toDate(
                        resultSet.getTimestamp(
                                tablePrefix + PAYMENT_DATE_FIELD)))
                .setTotalPrice(resultSet.getBigDecimal(
                        tablePrefix + TOTAL_PRICE_FIELD))
                .build();
    }
}
