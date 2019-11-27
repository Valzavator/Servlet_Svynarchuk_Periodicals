package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.dao.SubscriptionDao;
import com.gmail.maxsvynarchuk.persistence.dao.SubscriptionPlanDao;
import com.gmail.maxsvynarchuk.persistence.dao.factory.DaoFactory;
import com.gmail.maxsvynarchuk.persistence.entity.*;
import com.gmail.maxsvynarchuk.persistence.transaction.Transaction;
import com.gmail.maxsvynarchuk.service.exception.ServiceException;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Intermediate layer between command layer and dao layer.
 * Service responsible for processing subscription-related operations
 *
 * @author Maksym Svynarchuk
 */
public class SubscriptionService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(SubscriptionService.class);
    private SubscriptionDao subscriptionDao =
            DaoFactory.getInstance().getSubscriptionDao();
    private SubscriptionPlanDao subscriptionPlanDao =
            DaoFactory.getInstance().getSubscriptionPlanDao();
    private PaymentService paymentService =
            ServiceFactory.getPaymentService();
    private PeriodicalService periodicalService =
            ServiceFactory.getPeriodicalService();

    private SubscriptionService() {
    }

    private static class Singleton {

        private final static SubscriptionService INSTANCE = new SubscriptionService();
    }

    public static SubscriptionService getInstance() {
        return SubscriptionService.Singleton.INSTANCE;
    }

    public List<Subscription> findAllSubscriptionsByUserAndStatus(User user,
                                                                  boolean isExpired,
                                                                  long skip,
                                                                  long limit) {
        LOGGER.debug("Attempt to find all subscriptions by user and status");
        return subscriptionDao.findByUserAndStatus(user, isExpired, skip, limit);
    }

    public List<Subscription> findAllSubscriptionsByPayment(Payment payment) {
        LOGGER.debug("Attempt to find all subscriptions by payment");
        List<Subscription> subscriptions = subscriptionDao.findByPayment(payment);
        if (subscriptions.size() > 0) {
            return subscriptions;
        } else {
            LOGGER.error("Payment cannot exist without subscription: {}", payment);
            throw new ServiceException("Payment cannot exist without subscription!");
        }
    }

    public long getSubscriptionsCountByUserAndStatus(User user, boolean isExpired) {
        LOGGER.debug("Attempt to get active subscriptions count by user");
        return subscriptionDao.getCountByUserAndStatus(user, isExpired);
    }

    public void processSubscriptions(User user,
                                     List<Subscription> subscriptions,
                                     BigDecimal totalPrice) {
        LOGGER.debug("Attempt to process subscriptions");
        if (subscriptions.size() != 0) {
            Transaction.doTransaction(() -> {
                Payment payment = paymentService.createPayment(user, totalPrice);

                for (Subscription subscription : subscriptions) {
                    Periodical periodical =
                            periodicalService.findPeriodicalById(subscription.getPeriodical().getId())
                                    .orElseThrow(() -> new ServiceException(
                                            "Subscription cannot refer to a non-existent periodical"));
                    if (periodical.getStatus() == PeriodicalStatus.SUSPENDED) {
                        throw new ServiceException("Can't subscribe to periodical with SUSPEND status");
                    }

                    subscription.setPayment(payment);
                    LocalDate startDate = payment.getPaymentDateTime().toLocalDate();
                    LocalDate endDate = startDate.plusMonths(
                            subscription.getSubscriptionPlan().getMonthsAmount()
                    );
                    subscription.setStartDate(startDate);
                    subscription.setEndDate(endDate);
                    subscriptionDao.insert(subscription);
                }
            });
        }
    }

    public boolean isAlreadySubscribed(User user, Periodical periodical) {
        LOGGER.debug("Attempt to check that user is already subscribed");
        return subscriptionDao.isUserAlreadySubscribed(user, periodical);
    }

    public List<SubscriptionPlan> findAllSubscriptionPlans() {
        LOGGER.debug("Attempt to find all subscription plans");
        return subscriptionPlanDao.findAll();
    }

    public Optional<SubscriptionPlan> findSubscriptionPlanById(Integer id) {
        LOGGER.debug("Attempt to find subscription plan by id");
        return subscriptionPlanDao.findOne(id);
    }
}
