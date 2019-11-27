package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.dao.SubscriptionDao;
import com.gmail.maxsvynarchuk.persistence.dao.SubscriptionPlanDao;
import com.gmail.maxsvynarchuk.persistence.entity.*;
import com.gmail.maxsvynarchuk.provider.EntityProvider;
import com.gmail.maxsvynarchuk.service.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceTest {
    @InjectMocks
    private SubscriptionService subscriptionService = SubscriptionService.getInstance();
    @Mock
    private SubscriptionDao subscriptionDao;
    @Mock
    private SubscriptionPlanDao subscriptionPlanDao;
    @Mock
    private PaymentService paymentService;
    @Mock
    private PeriodicalService periodicalService;

    @Test
    void findAllSubscriptionsByUserAndStatusTest() {
        long skip = 0;
        long limit = 3;
        final boolean isExpired = false;
        User user = User.newBuilder()
                .setId(1L)
                .build();
        List<Subscription> expected = new ArrayList<Subscription>() {{
            add(Subscription.newBuilder().setId(1L).build());
            add(Subscription.newBuilder().setId(2L).build());
            add(Subscription.newBuilder().setId(3L).build());
        }};
        when(subscriptionDao.findByUserAndStatus(user, isExpired, skip, limit))
                .thenReturn(expected);

        List<Subscription> actual =
                subscriptionService.findAllSubscriptionsByUserAndStatus(user, isExpired, skip, limit);

        assertEquals(3, actual.size());
        verify(subscriptionDao, times(1))
                .findByUserAndStatus(user, isExpired, skip, limit);
    }

    @Test
    void findAllSubscriptionsByPaymentWithExistingSubscriptionsTest() {
        Payment payment = Payment.newBuilder()
                .setId(1L)
                .build();
        List<Subscription> expected = new ArrayList<Subscription>() {{
            add(Subscription.newBuilder().setId(1L).build());
            add(Subscription.newBuilder().setId(2L).build());
            add(Subscription.newBuilder().setId(3L).build());
        }};
        when(subscriptionDao.findByPayment(payment))
                .thenReturn(expected);

        List<Subscription> actual =
                subscriptionService.findAllSubscriptionsByPayment(payment);

        assertEquals(3, actual.size());
        verify(subscriptionDao, times(1)).findByPayment(payment);
    }

    @Test
    void findAllSubscriptionsByPaymentWithNoyExistingSubscriptionsTest() {
        Payment payment = Payment.newBuilder()
                .setId(1L)
                .build();
        when(subscriptionDao.findByPayment(payment))
                .thenReturn(Collections.emptyList());

        assertThrows(ServiceException.class, () ->
                subscriptionService.findAllSubscriptionsByPayment(payment));
        verify(subscriptionDao, times(1)).findByPayment(payment);
    }

    @Test
    void getSubscriptionsCountByUserAndStatusTest() {
        final boolean isExpired = false;
        User user = User.newBuilder()
                .setId(1L)
                .build();
        long expected = 10;
        when(subscriptionDao.getCountByUserAndStatus(user, isExpired))
                .thenReturn(expected);

        long actual =
                subscriptionService.getSubscriptionsCountByUserAndStatus(user, isExpired);

        assertEquals(expected, actual);
        verify(subscriptionDao, times(1))
                .getCountByUserAndStatus(user, isExpired);
    }

    @Test
    void processSubscriptionsTestWithNotValidData() {
        User user = User.newBuilder()
                .setId(1L)
                .build();
        List<Subscription> subscriptions = Collections.emptyList();
        BigDecimal totalPrice = new BigDecimal("10");

        subscriptionService.processSubscriptions(user, subscriptions, totalPrice);

        verify(paymentService, never()).createPayment(user, totalPrice);
        verify(periodicalService, never()).findPeriodicalById(anyLong());
        verify(subscriptionDao, never()).insert(any(Subscription.class));
    }

    @Test
    void isAlreadySubscribed() {
        final boolean isSubscribed = false;
        User user = User.newBuilder()
                .setId(1L)
                .build();
        Periodical periodical = Periodical.newBuilder()
                .setId(1L)
                .build();
        when(subscriptionDao.isUserAlreadySubscribed(user, periodical)).thenReturn(isSubscribed);

        assertFalse(subscriptionService.isAlreadySubscribed(user, periodical));
    }

    @Test
    void findAllSubscriptionPlans() {
        List<SubscriptionPlan> expected = new ArrayList<SubscriptionPlan>() {{
            add(EntityProvider.getOneMonthSubscriptionPlan());
            add(EntityProvider.getThreeMonthSubscriptionPlan());
            add(EntityProvider.getSixMonthSubscriptionPlan());
            add(EntityProvider.getTwelveMonthSubscriptionPlan());
        }};
        when(subscriptionPlanDao.findAll()).thenReturn(expected);

        List<SubscriptionPlan> actual = subscriptionService.findAllSubscriptionPlans();

        assertEquals(4, actual.size());
        verify(subscriptionPlanDao, times(1)).findAll();
    }

    @Test
    void findSubscriptionPlanByIdWithExistingSubscriptionPlanTest() {
        Integer subscriptionPlanId = 1;
        Optional<SubscriptionPlan> expected = Optional.of(
                SubscriptionPlan.newBuilder()
                        .setId(subscriptionPlanId)
                        .build());
        when(subscriptionPlanDao.findOne(subscriptionPlanId)).thenReturn(expected);

        Optional<SubscriptionPlan> actual =
                subscriptionService.findSubscriptionPlanById(subscriptionPlanId);

        assertEquals(expected, actual);
        verify(subscriptionPlanDao, times(1)).findOne(subscriptionPlanId);
    }

    @Test
    void findSubscriptionPlanByIdWithNotExistingSubscriptionPlanTest() {
        Integer subscriptionPlanId = 1;
        when(subscriptionPlanDao.findOne(subscriptionPlanId)).thenReturn(Optional.empty());

        Optional<SubscriptionPlan> periodicalOpt =
                subscriptionService.findSubscriptionPlanById(subscriptionPlanId);

        assertFalse(periodicalOpt.isPresent());
        verify(subscriptionPlanDao, times(1)).findOne(subscriptionPlanId);
    }
}