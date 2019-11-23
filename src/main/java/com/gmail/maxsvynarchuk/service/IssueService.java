package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.dao.*;
import com.gmail.maxsvynarchuk.persistence.dao.factory.DaoFactory;
import com.gmail.maxsvynarchuk.persistence.entity.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Intermediate layer between command layer and dao layer.
 * Service responsible for processing issue-related operations
 *
 * @author Maksym Svynarchuk
 */
public class IssueService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(IssueService.class);
    private PeriodicalIssueDao periodicalIssueDao =
            DaoFactory.getInstance().getPeriodicalIssueDao();

    private IssueService() {
    }

    private static class Singleton {
        private final static IssueService INSTANCE = new IssueService();
    }

    public static IssueService getInstance() {
        return IssueService.Singleton.INSTANCE;
    }

    public boolean addIssueToPeriodical(Periodical periodical, PeriodicalIssue periodicalIssue) {
        LOGGER.debug("Attempt to add issue to periodical");
        boolean issueIsPresent = periodicalIssueDao.existByNumberAndPeriodical(
                periodicalIssue.getIssueNumber(),
                periodical);
        if (!issueIsPresent) {
            periodicalIssue.setPeriodical(periodical);
            periodicalIssueDao.insert(periodicalIssue);
            return true;
        }
        return false;
    }

    public List<PeriodicalIssue> findAllIssuesByPeriodical(Periodical periodical) {
        LOGGER.debug("Attempt to find all issues by periodical");
        return periodicalIssueDao.findByPeriodical(periodical);
    }
}
