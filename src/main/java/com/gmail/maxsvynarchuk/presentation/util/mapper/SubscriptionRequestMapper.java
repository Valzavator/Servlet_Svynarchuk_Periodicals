package com.gmail.maxsvynarchuk.presentation.util.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriptionRequestMapper implements RequestEntityMapper<Subscription> {
    @Override
    public Subscription mapToObject(HttpServletRequest request) {
        throw new NotImplementedException();
//                User tempUser = userMapper.mapToObject(resultSet);
//        Payment tempPayment = paymentMapper.mapToObject(resultSet);
//        Periodical tempPeriodical = periodicalMapper.mapToObject(resultSet);
//        SubscriptionPlan tempSubscriptionPlan = subscriptionPlanMapper.mapToObject(resultSet);
//
//        return Subscription.newBuilder()
//                .setId(resultSet.getLong(
//                        tablePrefix + ID_FIELD))
//                .setUser(tempUser)
//                .setPayment(tempPayment)
//                .setPeriodical(tempPeriodical)
//                .setSubscriptionPlan(tempSubscriptionPlan)
//                .setStartDate(resultSet.getDate(
//                        tablePrefix + START_DATE_FIELD))
//                .setEndDate(resultSet.getDate(
//                        tablePrefix + END_DATE_FIELD))
//                .build();
    }
}
