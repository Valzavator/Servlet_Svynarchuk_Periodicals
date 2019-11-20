package com.gmail.maxsvynarchuk.persistence.dao;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;

import java.util.List;

public interface PeriodicalDao extends GenericDao<Periodical, Long> {
    /**
     * Retrieves all periodicals associated with certain periodical status.
     *
     * @param status status of periodical
     * @param skip   skip
     * @param limit  limit
     * @return list of retrieved periodicals
     */
    List<Periodical> findByStatus(PeriodicalStatus status, long skip, long limit);

    /**
     * Retrieves count of objects from database.
     *
     * @param status status of periodical
     * @return count of objects with same status.
     */
    long getCountByStatus(PeriodicalStatus status);
}
