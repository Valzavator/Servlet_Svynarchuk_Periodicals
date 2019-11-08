package com.gmail.maxsvynarchuk.persistence.dao;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.User;

import java.util.List;

public interface PeriodicalDao extends GenericDao<Periodical, Long> {
    List<Periodical> findAllOnWhichUserIsNotSubscribed(int skip, int limit, User user);
}
