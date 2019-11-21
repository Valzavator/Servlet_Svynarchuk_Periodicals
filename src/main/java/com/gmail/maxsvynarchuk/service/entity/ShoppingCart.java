package com.gmail.maxsvynarchuk.service.entity;

import com.gmail.maxsvynarchuk.persistence.entity.Subscription;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents virtual shopping cart for storing items
 */
public class ShoppingCart {
    private final List<Subscription> items = new ArrayList<>();

    public boolean addItem(Subscription subscription) {
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

    public int size() {
        return items.size();
    }

    public boolean isHasSuspendedPeriodical() {
        return items.stream().anyMatch(this::isSuspendedPeriodical);
    }

    public void updateItem(Subscription updatedSubscription) {
        removeItem(getCartItemId(updatedSubscription));
        items.add(updatedSubscription);
    }

    private boolean isInCart(Subscription subscriptionToCheck) {
        long cartItemId = getCartItemId(subscriptionToCheck);
        return items.stream().anyMatch(item -> getCartItemId(item) == cartItemId);
    }

    private long getCartItemId(Subscription subscription) {
        return subscription.getPeriodical().getId();
    }

    private boolean isSuspendedPeriodical(Subscription subscription) {
        return subscription.getPeriodical().getStatus() == PeriodicalStatus.SUSPENDED;
    }
}
