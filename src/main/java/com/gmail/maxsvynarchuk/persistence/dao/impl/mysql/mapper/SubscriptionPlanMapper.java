package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.SubscriptionPlan;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriptionPlanMapper implements EntityMapper<SubscriptionPlan> {
    private static final String ID_FIELD = "subscription_plan_id";
    private static final String PLAN_NAME_FIELD = "plan_name";
    private static final String MONTHS_AMOUNT_FIELD = "months_amount";
    private static final String RATE_FIELD = "rate";
    private static final String PLAN_DESCRIPTION_FIELD = "plan_description";

    @Override
    public SubscriptionPlan mapToObject(ResultSet resultSet, String tablePrefix)
            throws SQLException {
        return SubscriptionPlan.newBuilder()
                .setId(resultSet.getInt(
                        tablePrefix + ID_FIELD))
                .setName(resultSet.getString(
                        tablePrefix + PLAN_NAME_FIELD))
                .setMonthsAmount(resultSet.getInt(
                        tablePrefix + MONTHS_AMOUNT_FIELD))
                .setRate(resultSet.getBigDecimal(
                        tablePrefix + RATE_FIELD))
                .setDescription(resultSet.getString(
                        tablePrefix + PLAN_DESCRIPTION_FIELD))
                .build();
    }
}
