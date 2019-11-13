package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.dao.*;
import com.gmail.maxsvynarchuk.persistence.dao.factory.DaoFactory;
import com.gmail.maxsvynarchuk.persistence.entity.*;

import java.util.List;
import java.util.Optional;

public class PeriodicalService {
    private final PeriodicalDao periodicalDao = DaoFactory.getInstance().getPeriodicalDao();
    private final PeriodicalTypeDao periodicalTypeDao = DaoFactory.getInstance().getPeriodicalTypeDao();
    private final FrequencyDao frequencyDao = DaoFactory.getInstance().getFrequencyDao();
    private final PublisherDao publisherDao = DaoFactory.getInstance().getPublisherDao();

    private PeriodicalService() {
    }

    private static class Singleton {
        private final static PeriodicalService INSTANCE = new PeriodicalService();
    }

    public static PeriodicalService getInstance() {
        return PeriodicalService.Singleton.INSTANCE;
    }

    public Optional<Periodical> findById(Long id) {
        return periodicalDao.findOne(id);
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

    public List<PeriodicalType> getAllPeriodicalTypes() {
        return periodicalTypeDao.findAll();
    }

    public List<Frequency> getAllFrequencies() {
        return frequencyDao.findAll();
    }

    public List<Publisher> getAllPublishers() {
        return publisherDao.findAll();
    }
}
