package com.gmail.maxsvynarchuk.persistence.dao;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.util.PeriodicalStatus;

import java.util.List;

//TODO write comments
public interface PeriodicalDao extends GenericDao<Periodical, Long> {
    List<Periodical> findAllPeriodicalsByStatus(PeriodicalStatus status, long skip, long limit);

    /**
     * Retrieves count of objects from database.
     *
     * @return count of objects.
     */
    long getCountByStatus(PeriodicalStatus periodicalStatus);
}
