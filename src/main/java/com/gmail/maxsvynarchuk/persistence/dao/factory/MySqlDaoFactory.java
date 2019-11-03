package com.gmail.maxsvynarchuk.persistence.dao.factory;

import com.gmail.maxsvynarchuk.persistence.dao.*;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.*;

public class MySqlDaoFactory extends DaoFactory {
    @Override
    public AddressDao getAddressDao() {
        return new AddressMySqlDao();
    }

    @Override
    public FrequencyDao getFrequencyDao() {
        return new FrequencyMySqlDao();
    }

    @Override
    public PaymentDao getPaymentDao() {
        return new PaymentMySqlDao();
    }

    @Override
    public PeriodicalDao getPeriodicalDao() {
        return new PeriodicalMySqlDao();
    }

    @Override
    public PeriodicalIssueDao getPeriodicalIssueDao() {
        return new PeriodicalIssueMySqlDao();
    }

    @Override
    public PeriodicalTypeDao getPeriodicalTypeDao() {
        return new PeriodicalTypeMySqlDao();
    }

    @Override
    public PublisherDao getPublisherDao() {
        return new PublisherMySqlDao();
    }

    @Override
    public RoleDao getRoleDao() {
        return new RoleMySqlDao();
    }

    @Override
    public SubscriptionDao getSubscriptionDao() {
        return new SubscriptionMySqlDao();
    }

    @Override
    public SubscriptionPlanDao getSubscriptionPlanDao() {
        return new SubscriptionPlanMySqlDao();
    }

    @Override
    public UserDao getUserDao() {
        return new UserMySqlDao();
    }
}
