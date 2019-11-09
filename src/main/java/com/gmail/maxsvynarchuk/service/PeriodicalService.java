package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.dao.PeriodicalDao;
import com.gmail.maxsvynarchuk.persistence.dao.SubscriptionPlanDao;
import com.gmail.maxsvynarchuk.persistence.dao.factory.DaoFactory;
import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.User;

import java.util.List;

public class PeriodicalService {
    private final PeriodicalDao periodicalDao = DaoFactory.getInstance().getPeriodicalDao();

    private PeriodicalService() {
    }

    private static class Singleton {
        private final static PeriodicalService INSTANCE = new PeriodicalService();
    }

    public static PeriodicalService getInstance() {
        return PeriodicalService.Singleton.INSTANCE;
    }

    public List<Periodical> getPeriodicals(long skip, long limit) {
        return periodicalDao.findAll(skip, limit);
    }
//
//    public List<Periodical> getPeriodicalsOnWhichUserIsNotSubscribed(int skip, int limit, User user) {
//        return periodicalDao.findAllOnWhichUserIsNotSubscribed(skip, limit, user);
//    }

    public long getPeriodicalsCount() {
        return periodicalDao.getCount();
    }
}
