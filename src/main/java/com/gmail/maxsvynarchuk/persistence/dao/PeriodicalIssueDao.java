package com.gmail.maxsvynarchuk.persistence.dao;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.PeriodicalIssue;

import java.util.List;
import java.util.Optional;

public interface PeriodicalIssueDao extends GenericDao<PeriodicalIssue, Long> {

    /**
     * Retrieves all issues associated with certain periodical.
     *
     * @param periodical periodical to retrieve issues related with him
     * @return optional, which contains retrieved object or {@code null}
     */
    List<PeriodicalIssue> findByPeriodical(Periodical periodical);
    /**
     * Retrieve issue from database identified by issue number and periodical.
     *
     * @param issueNumber number of issue
     * @param periodical periodical to retrieve issues related with him
     * @return optional, which contains retrieved object or {@code null}
     */
    Optional<PeriodicalIssue> findOneByNumberAndPeriodical(String issueNumber, Periodical periodical);

    /**
     * Check if issue exists in database.
     *
     * @param issueNumber number of issue
     * @param periodical periodical to retrieve issues related with him
     * @return {@code true} if exists else {@code false}
     */
    boolean existByNumberAndPeriodical(String issueNumber, Periodical periodical);
}
