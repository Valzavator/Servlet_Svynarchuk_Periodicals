package com.gmail.maxsvynarchuk.service.entity;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.Subscription;
import com.gmail.maxsvynarchuk.service.PeriodicalService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents virtual shopping cart for storing items
 */
public class ShoppingCart {
    private final PeriodicalService periodicalService =
            ServiceFactory.getPeriodicalService();
    private final List<Subscription> items = new ArrayList<>();

    public boolean addItem(Subscription subscription) {
        Objects.requireNonNull(subscription);
        if (isInCart(subscription)) {
            return false;
        }
        return items.add(subscription);
    }

    public void removeItem(long cartItemId) {
        items.removeIf(item -> getCartItemId(item) == cartItemId);
    }

    public void removeAll() {
        items.clear();
    }

    public List<Subscription> getItems() {
        updateDateFromDatabase();
        return new ArrayList<>(items);
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalValue = new BigDecimal(0);
        for (Subscription subscription : items) {
            BigDecimal monthAmount =
                    new BigDecimal(subscription.getSubscriptionPlan().getMonthsAmount());
            BigDecimal rate = subscription.getSubscriptionPlan().getRate();
            BigDecimal price = subscription.getPeriodical().getPrice();
            totalValue = totalValue.add(
                    price.multiply(
                            monthAmount.multiply(rate)));
        }
        return totalValue.setScale(2, RoundingMode.HALF_EVEN);
    }

    public boolean isInCart(Subscription subscriptionToCheck) {
        long cartItemId = getCartItemId(subscriptionToCheck);
        return items.stream().anyMatch(item -> getCartItemId(item) == cartItemId);
    }

    public int size() {
        return items.size();
    }

    public boolean isHasSuspendedPeriodical() {
        return items.stream().anyMatch(
                item -> item.getPeriodical().getStatus() == PeriodicalStatus.SUSPENDED);
    }

    private void updateDateFromDatabase() {
        for (Subscription subscription : items) {
            Optional<Periodical> periodicalOpt =
                    periodicalService.findPeriodicalById(subscription.getPeriodical().getId());
            if (periodicalOpt.isPresent()) {
                subscription.setPeriodical(periodicalOpt.get());
            } else {
                removeItem(getCartItemId(subscription));
            }
        }
    }

    private long getCartItemId(Subscription subscription) {
        return subscription.getPeriodical().getId();
    }
}
