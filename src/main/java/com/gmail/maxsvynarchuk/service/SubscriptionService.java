package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.dao.PeriodicalDao;
import com.gmail.maxsvynarchuk.persistence.dao.SubscriptionDao;
import com.gmail.maxsvynarchuk.persistence.dao.SubscriptionPlanDao;
import com.gmail.maxsvynarchuk.persistence.dao.factory.DaoFactory;
import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.SubscriptionPlan;
import com.gmail.maxsvynarchuk.persistence.entity.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SubscriptionService {
    private final SubscriptionDao subscriptionDao = DaoFactory.getInstance().getSubscriptionDao();
    private final SubscriptionPlanDao subscriptionPlanDao = DaoFactory.getInstance().getSubscriptionPlanDao();
    private final PeriodicalDao periodicalDao = DaoFactory.getInstance().getPeriodicalDao();

    private SubscriptionService() {
    }

    private static class Singleton {
        private final static SubscriptionService INSTANCE = new SubscriptionService();
    }

    public static SubscriptionService getInstance() {
        return SubscriptionService.Singleton.INSTANCE;
    }

    public boolean isAlreadySubscribed(User user, Periodical periodical) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(periodical);

        return subscriptionDao.isUserAlreadySubscribed(user, periodical);
    }

    public List<SubscriptionPlan> findAllSubscriptionPlans() {
        return subscriptionPlanDao.findAll();
    }

    public Optional<SubscriptionPlan> findSubscriptionPlanById(Integer id) {
        return subscriptionPlanDao.findOne(id);
    }

}
