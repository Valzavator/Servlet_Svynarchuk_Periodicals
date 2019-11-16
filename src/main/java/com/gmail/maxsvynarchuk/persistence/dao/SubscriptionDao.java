package com.gmail.maxsvynarchuk.persistence.dao;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.Subscription;
import com.gmail.maxsvynarchuk.persistence.entity.User;

public interface SubscriptionDao extends GenericDao<Subscription, Long> {

    /**
     * Check if user has subscribed to periodical on the current date.
     *
     * @param user user for checked
     * @param periodical periodical to retrieve subscription related with him
     * @return {@code true} if user is already subscribed else {@code false}
     */
    boolean isUserAlreadySubscribed(User user, Periodical periodical);
}
