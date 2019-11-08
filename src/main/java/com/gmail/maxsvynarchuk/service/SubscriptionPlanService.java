package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.dao.SubscriptionPlanDao;
import com.gmail.maxsvynarchuk.persistence.dao.factory.DaoFactory;
import com.gmail.maxsvynarchuk.persistence.entity.SubscriptionPlan;

import java.util.List;

public class SubscriptionPlanService {
    private final SubscriptionPlanDao subscriptionPlan = DaoFactory.getInstance().getSubscriptionPlanDao();

    private SubscriptionPlanService() {
    }

    private static class Singleton {
        private final static SubscriptionPlanService INSTANCE = new SubscriptionPlanService();
    }

    public static SubscriptionPlanService getInstance() {
        return SubscriptionPlanService.Singleton.INSTANCE;
    }

    public List<SubscriptionPlan> getAllSubscriptionPlans() {
        return subscriptionPlan.findAll();
    }
}
