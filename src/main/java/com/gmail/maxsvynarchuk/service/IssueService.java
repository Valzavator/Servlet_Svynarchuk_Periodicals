package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.dao.*;
import com.gmail.maxsvynarchuk.persistence.dao.factory.DaoFactory;
import com.gmail.maxsvynarchuk.persistence.entity.*;

import java.util.List;
import java.util.Objects;

/**
 * Intermediate layer between command layer and dao layer.
 * Service responsible for processing issue-related operations
 *
 * @author Maksym Svynarchuk
 */
public class IssueService {
    private final PeriodicalIssueDao periodicalIssueDao =
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
        Objects.requireNonNull(periodical);
        Objects.requireNonNull(periodicalIssue);

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
        Objects.requireNonNull(periodical);

        return periodicalIssueDao.findByPeriodical(periodical);
    }
}
