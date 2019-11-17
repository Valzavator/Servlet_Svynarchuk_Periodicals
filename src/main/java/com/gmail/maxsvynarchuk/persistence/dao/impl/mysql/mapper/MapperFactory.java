package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.*;

public class MapperFactory {
    private static final EntityMapper<Frequency> FREQUENCY_MAPPER = new FrequencyMapper();
    private static final EntityMapper<Payment> PAYMENT_MAPPER = new PaymentMapper();
    private static final EntityMapper<PeriodicalIssue> PERIODICAL_ISSUE_MAPPER = new PeriodicalIssueMapper();
    private static final EntityMapper<Periodical> PERIODICAL_MAPPER = new PeriodicalMapper();
    private static final EntityMapper<PeriodicalType> PERIODICAL_TYPE_MAPPER = new PeriodicalTypeMapper();
    private static final EntityMapper<Publisher> PUBLISHER_MAPPER = new PublisherMapper();
    private static final EntityMapper<Role> ROLE_MAPPER = new RoleMapper();
    private static final EntityMapper<Subscription> SUBSCRIPTION_MAPPER = new SubscriptionMapper();
    private static final EntityMapper<SubscriptionPlan> SUBSCRIPTION_PLAN_MAPPER = new SubscriptionPlanMapper();
    private static final EntityMapper<User> USER_MAPPER = new UserMapper();

    public static EntityMapper<Frequency> getFrequencyMapper() {
        return FREQUENCY_MAPPER;
    }

    public static EntityMapper<Payment> getPaymentMapper() {
        return PAYMENT_MAPPER;
    }

    public static EntityMapper<PeriodicalIssue> getPeriodicalIssueMapper() {
        return PERIODICAL_ISSUE_MAPPER;
    }

    public static EntityMapper<Periodical> getPeriodicalMapper() {
        return PERIODICAL_MAPPER;
    }

    public static EntityMapper<PeriodicalType> getPeriodicalTypeMapper() {
        return PERIODICAL_TYPE_MAPPER;
    }

    public static EntityMapper<Publisher> getPublisherMapper() {
        return PUBLISHER_MAPPER;
    }

    public static EntityMapper<Role> getRoleMapper() {
        return ROLE_MAPPER;
    }

    public static EntityMapper<Subscription> getSubscriptionMapper() {
        return SUBSCRIPTION_MAPPER;
    }

    public static EntityMapper<SubscriptionPlan> getSubscriptionPlanMapper() {
        return SUBSCRIPTION_PLAN_MAPPER;
    }

    public static EntityMapper<User> getUserMapper() {
        return USER_MAPPER;
    }
}
