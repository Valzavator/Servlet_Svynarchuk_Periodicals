package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SubscriptionMapper implements EntityMapper<Subscription> {
    private static final String ID_FIELD = "subscription_id";
    private static final String START_DATE_FIELD = "start_date";
    private static final String END_DATE_FIELD = "end_date";

    private final EntityMapper<User> userMapper;
    private final EntityMapper<Payment> paymentMapper;
    private final EntityMapper<Periodical> periodicalMapper;
    private final EntityMapper<SubscriptionPlan> subscriptionPlanMapper;

    public SubscriptionMapper() {
        this(new UserMapper(),
                new PaymentMapper(),
                new PeriodicalMapper(),
                new SubscriptionPlanMapper());
    }

    public SubscriptionMapper(EntityMapper<User> userMapper,
                              EntityMapper<Payment> paymentMapper,
                              EntityMapper<Periodical> periodicalMapper,
                              EntityMapper<SubscriptionPlan> subscriptionPlanMapper) {
        this.userMapper = userMapper;
        this.paymentMapper = paymentMapper;
        this.periodicalMapper = periodicalMapper;
        this.subscriptionPlanMapper = subscriptionPlanMapper;
    }

    @Override
    public Subscription mapToObject(ResultSet resultSet, String tablePrefix) throws SQLException {
        User tempUser = userMapper.mapToObject(resultSet);
        Payment tempPayment = paymentMapper.mapToObject(resultSet);
        Periodical tempPeriodical = periodicalMapper.mapToObject(resultSet);
        SubscriptionPlan tempSubscriptionPlan = subscriptionPlanMapper.mapToObject(resultSet);

        return Subscription.newBuilder()
                .setId(resultSet.getLong(
                        tablePrefix + ID_FIELD))
                .setUser(tempUser)
                .setPayment(tempPayment)
                .setPeriodical(tempPeriodical)
                .setSubscriptionPlan(tempSubscriptionPlan)
                .setStartDate(resultSet.getObject(
                        tablePrefix + START_DATE_FIELD, LocalDate.class))
                .setEndDate(resultSet.getObject(
                        tablePrefix + END_DATE_FIELD,  LocalDate.class))
                .build();
    }
}
