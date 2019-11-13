package com.gmail.maxsvynarchuk.persistence.dao.factory;

import com.gmail.maxsvynarchuk.persistence.dao.*;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.*;

public class MySqlDaoFactory extends DaoFactory {
    private UserDao userDao;
    private RoleDao roleDao;
    private AddressDao addressDao;
    private PeriodicalDao periodicalDao;
    private PeriodicalTypeDao periodicalTypeDao;
    private FrequencyDao frequencyDao;
    private PublisherDao publisherDao;
    private PeriodicalIssueDao periodicalIssueDao;
    private PaymentDao paymentDao;
    private SubscriptionDao subscriptionDao;
    private SubscriptionPlanDao subscriptionPlanDao;

    public MySqlDaoFactory() {
        this.userDao = new UserMySqlDao();
        this.roleDao = new RoleMySqlDao();
        this.addressDao = new AddressMySqlDao();
        this.periodicalDao = new PeriodicalMySqlDao();
        this.periodicalTypeDao = new PeriodicalTypeMySqlDao();
        this.frequencyDao = new FrequencyMySqlDao();
        this.publisherDao = new PublisherMySqlDao();
        this.periodicalIssueDao = new PeriodicalIssueMySqlDao();
        this.paymentDao = new PaymentMySqlDao();
        this.subscriptionDao = new SubscriptionMySqlDao();
        this.subscriptionPlanDao = new SubscriptionPlanMySqlDao();
    }

    @Override
    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public RoleDao getRoleDao() {
        return roleDao;
    }

    @Override
    public AddressDao getAddressDao() {
        return addressDao;
    }

    @Override
    public PeriodicalDao getPeriodicalDao() {
        return periodicalDao;
    }

    @Override
    public PeriodicalTypeDao getPeriodicalTypeDao() {
        return periodicalTypeDao;
    }

    @Override
    public FrequencyDao getFrequencyDao() {
        return frequencyDao;
    }

    @Override
    public PublisherDao getPublisherDao() {
        return publisherDao;
    }

    @Override
    public PeriodicalIssueDao getPeriodicalIssueDao() {
        return periodicalIssueDao;
    }

    @Override
    public PaymentDao getPaymentDao() {
        return paymentDao;
    }

    @Override
    public SubscriptionDao getSubscriptionDao() {
        return subscriptionDao;
    }

    @Override
    public SubscriptionPlanDao getSubscriptionPlanDao() {
        return subscriptionPlanDao;
    }
}
