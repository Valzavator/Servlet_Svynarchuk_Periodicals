package com.gmail.maxsvynarchuk.provider;

import com.gmail.maxsvynarchuk.persistence.entity.*;
import com.gmail.maxsvynarchuk.util.type.Gender;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;
import com.gmail.maxsvynarchuk.util.type.RoleType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EntityProvider {
    public static final EntityProvider INSTANCE = new EntityProvider();

    private EntityProvider() {
    }

    public static EntityProvider getInstance() {
        return INSTANCE;
    }

    public static Role getUserRole() {
        return RoleType.USER.getValue();
    }

    public static User getUser(Long id) {
        return User.newBuilder()
                .setId(id)
                .setRole(getUserRole())
                .setFirstName("Firstname")
                .setLastName("Secondname")
                .setEmail("user@gmail.com")
                .setPassword("password")
                .setDateOfBirth(LocalDate.now())
                .setGender(Gender.MALE)
                .build();
    }

    public static Periodical getPeriodical(Long id,
                                           PeriodicalStatus status,
                                           String price) {
        return Periodical.newBuilder()
                .setId(id)
                .setStatus(status)
                .setPrice(new BigDecimal(price))
                .build();
    }

    public static PeriodicalIssue getPeriodicalIssue(Long id, Periodical periodical) {
        return PeriodicalIssue.newBuilder()
                .setId(id)
                .setPeriodical(periodical)
                .build();
    }

    public static Subscription getSubscriptionForCard(Periodical periodical,
                                                      SubscriptionPlan subscriptionPlan) {
        return Subscription.newBuilder()
                .setPeriodical(periodical)
                .setSubscriptionPlan(subscriptionPlan)
                .build();
    }

    public static Subscription getSubscriptionForService(Long id,
                                                         User user,
                                                         Payment payment,
                                                         Periodical periodical,
                                                         SubscriptionPlan subscriptionPlan) {
        return Subscription.newBuilder()
                .setId(id)
                .setUser(user)
                .setPayment(payment)
                .setPeriodical(periodical)
                .setSubscriptionPlan(subscriptionPlan)
                .build();
    }

    public static SubscriptionPlan getOneMonthSubscriptionPlan() {
        return SubscriptionPlan.newBuilder()
                .setMonthsAmount(1)
                .setRate(new BigDecimal(1))
                .build();
    }

    public static SubscriptionPlan getThreeMonthSubscriptionPlan() {
        return SubscriptionPlan.newBuilder()
                .setMonthsAmount(3)
                .setRate(new BigDecimal("0.9"))
                .build();
    }

    public static SubscriptionPlan getSixMonthSubscriptionPlan() {
        return SubscriptionPlan.newBuilder()
                .setMonthsAmount(6)
                .setRate(new BigDecimal("0.8"))
                .build();
    }

    public static SubscriptionPlan getTwelveMonthSubscriptionPlan() {
        return SubscriptionPlan.newBuilder()
                .setMonthsAmount(12)
                .setRate(new BigDecimal("0.7"))
                .build();
    }

    public static List<Subscription> getSubscriptionsWithActivePeriodicals() {
        return Collections.unmodifiableList(new ArrayList<Subscription>() {{
            add(EntityProvider.getSubscriptionForCard(
                    EntityProvider.getPeriodical(1L, PeriodicalStatus.ACTIVE, "1"),
                    EntityProvider.getOneMonthSubscriptionPlan()));

            add(EntityProvider.getSubscriptionForCard(
                    EntityProvider.getPeriodical(2L, PeriodicalStatus.ACTIVE, "2"),
                    EntityProvider.getThreeMonthSubscriptionPlan()));

            add(EntityProvider.getSubscriptionForCard(
                    EntityProvider.getPeriodical(3L, PeriodicalStatus.ACTIVE, "3"),
                    EntityProvider.getSixMonthSubscriptionPlan()));

            add(EntityProvider.getSubscriptionForCard(
                    EntityProvider.getPeriodical(4L, PeriodicalStatus.ACTIVE, "4"),
                    EntityProvider.getTwelveMonthSubscriptionPlan()));
        }});
    }

    public static Subscription getSubscriptionWithActivePeriodical() {
        return EntityProvider.getSubscriptionForCard(
                EntityProvider.getPeriodical(5L, PeriodicalStatus.ACTIVE, "10"),
                EntityProvider.getTwelveMonthSubscriptionPlan());
    }

    public static Subscription getSubscriptionWithSuspendedPeriodical() {
        return EntityProvider.getSubscriptionForCard(
                EntityProvider.getPeriodical(6L, PeriodicalStatus.SUSPENDED, "10"),
                EntityProvider.getTwelveMonthSubscriptionPlan());
    }
}
