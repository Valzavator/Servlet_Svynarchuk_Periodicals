package com.gmail.maxsvynarchuk.persistence.dao;

import com.gmail.maxsvynarchuk.persistence.entity.Payment;
import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.Subscription;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;

import java.util.List;

public interface SubscriptionDao extends GenericDao<Subscription, Long> {

    /**
     * Check if user has subscribed to periodical on the current date.
     *
     * @param user       user for checked
     * @param periodical periodical to retrieve subscription related with him
     * @return {@code true} if user is already subscribed else {@code false}
     */
    boolean isUserAlreadySubscribed(User user, Periodical periodical);

    /**
     * Retrieves all active subscriptions associated with certain user.
     *
     * @param user  user of system
     * @param skip  skip
     * @param limit limit
     * @return list of retrieved subscriptions
     */
    List<Subscription> findActiveByUser(User user, long skip, long limit);

    /**
     * Retrieves all subscriptions associated with certain payment.
     *
     * @param payment  payment
     * @return list of retrieved subscriptions
     */
    List<Subscription> findByPayment(Payment payment);

    /**
     * Retrieves count of objects from database.
     *
     * @param user user of system
     * @return count of active subscriptions associated with certain user.
     */
    long getCountActiveByUser(User user);
}
