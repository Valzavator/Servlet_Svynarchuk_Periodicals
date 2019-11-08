package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.dao.PeriodicalDao;
import com.gmail.maxsvynarchuk.persistence.dao.factory.DaoFactory;
import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.User;

import java.util.List;

public class SubscriptionService {
    private final PeriodicalDao periodicalDao = DaoFactory.getInstance().getPeriodicalDao();

    private SubscriptionService() {
    }

    private static class Singleton {
        private final static SubscriptionService INSTANCE = new SubscriptionService();
    }

    public static SubscriptionService getInstance() {
        return SubscriptionService.Singleton.INSTANCE;
    }

    public boolean isAlreadySubscribed(Long userId, Long periodicalId) {
        return true;
    }
}
