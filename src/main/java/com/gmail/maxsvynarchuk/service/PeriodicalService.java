package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.dao.*;
import com.gmail.maxsvynarchuk.persistence.dao.factory.DaoFactory;
import com.gmail.maxsvynarchuk.persistence.entity.*;
import com.gmail.maxsvynarchuk.util.PeriodicalStatus;
import jdk.net.SocketFlow;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PeriodicalService {
    private final PeriodicalDao periodicalDao = DaoFactory.getInstance().getPeriodicalDao();
    private final PeriodicalIssueDao periodicalIssueDao = DaoFactory.getInstance().getPeriodicalIssueDao();
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

    public Periodical createPeriodical(Periodical periodical) {
        Objects.requireNonNull(periodical);

        return periodicalDao.insert(periodical);
    }

    public void updatePeriodical(Periodical periodical) {
        Objects.requireNonNull(periodical);

        periodicalDao.update(periodical);
    }

    public void changeStatus(Periodical periodical, PeriodicalStatus newStatus) {
        Objects.requireNonNull(periodical);
        Objects.requireNonNull(newStatus);

        if (periodical.getStatus() != newStatus) {
            periodical.setStatus(newStatus);
            updatePeriodical(periodical);
        }
    }

    public Optional<Periodical> findPeriodicalById(Long id) {
        return periodicalDao.findOne(id);
    }

    public List<Periodical> findAllPeriodicals(long skip, long limit) {
        return periodicalDao.findAll(skip, limit);
    }

    public List<Periodical> findAllPeriodicalsByStatus(PeriodicalStatus status, long skip, long limit) {
        return periodicalDao.findAllPeriodicalsByStatus(status, skip, limit);
    }

    public long getPeriodicalsCountByStatus(PeriodicalStatus status) {
        return periodicalDao.getCountByStatus(status);
    }

    public long getPeriodicalsCount() {
        return periodicalDao.getCount();
    }

    public Optional<PeriodicalType> findPeriodicalTypeById(Integer id) {
        return periodicalTypeDao.findOne(id);
    }

    public List<PeriodicalType> findAllPeriodicalTypes() {
        return periodicalTypeDao.findAll();
    }

    public Optional<Frequency> findFrequencyById(Integer id) {
        return frequencyDao.findOne(id);
    }

    public List<Frequency> findAllFrequencies() {
        return frequencyDao.findAll();
    }

    public Optional<Publisher> findPublisherById(Long id) {
        return publisherDao.findOne(id);
    }

    public List<Publisher> findAllPublishers() {
        return publisherDao.findAll();
    }
}
