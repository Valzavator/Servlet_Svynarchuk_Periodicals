package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.dao.*;
import com.gmail.maxsvynarchuk.persistence.dao.factory.DaoFactory;
import com.gmail.maxsvynarchuk.persistence.entity.*;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

/**
 * Intermediate layer between command layer and dao layer.
 * Service responsible for processing periodical-related operations
 *
 * @author Maksym Svynarchuk
 */
public class PeriodicalService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(PeriodicalService.class);
    private PeriodicalDao periodicalDao =
            DaoFactory.getInstance().getPeriodicalDao();
    private PeriodicalTypeDao periodicalTypeDao =
            DaoFactory.getInstance().getPeriodicalTypeDao();
    private FrequencyDao frequencyDao =
            DaoFactory.getInstance().getFrequencyDao();
    private PublisherDao publisherDao =
            DaoFactory.getInstance().getPublisherDao();

    private PeriodicalService() {
    }

    private static class Singleton {
        private final static PeriodicalService INSTANCE = new PeriodicalService();
    }

    public static PeriodicalService getInstance() {
        return PeriodicalService.Singleton.INSTANCE;
    }

    public Periodical createPeriodical(Periodical periodical) {
        return periodicalDao.insert(periodical);
    }

    public void updatePeriodical(Periodical periodical) {
        LOGGER.debug("Attempt to update periodical");
        periodicalDao.update(periodical);
    }

    public void changeStatus(Periodical periodical, PeriodicalStatus newStatus) {
        LOGGER.debug("Attempt to change status of periodical");
        if (periodical.getStatus() != newStatus) {
            periodical.setStatus(newStatus);
            updatePeriodical(periodical);
        }
    }

    public Optional<Periodical> findPeriodicalById(Long id) {
        LOGGER.debug("Attempt to find periodical by id");
        return periodicalDao.findOne(id);
    }

    public List<Periodical> findAllPeriodicals(long skip, long limit) {
        LOGGER.debug("Attempt to find all periodicals");
        return periodicalDao.findAll(skip, limit);
    }

    public List<Periodical> findAllPeriodicalsByStatus(PeriodicalStatus status, long skip, long limit) {
        LOGGER.debug("Attempt to find all periodicals by status");
        return periodicalDao.findByStatus(status, skip, limit);
    }

    public long getPeriodicalsCountByStatus(PeriodicalStatus status) {
        LOGGER.debug("Attempt to get count of periodicals by status");
        return periodicalDao.getCountByStatus(status);
    }

    public long getPeriodicalsCount() {
        LOGGER.debug("Attempt to get count of periodicals");
        return periodicalDao.getCount();
    }

    public List<PeriodicalType> findAllPeriodicalTypes() {
        LOGGER.debug("Attempt to find all periodical types");
        return periodicalTypeDao.findAll();
    }

    public List<Frequency> findAllFrequencies() {
        LOGGER.debug("Attempt to find all frequencies");
        return frequencyDao.findAll();
    }

    public List<Publisher> findAllPublishers() {
        LOGGER.debug("Attempt to find all publishers");
        return publisherDao.findAll();
    }
}
