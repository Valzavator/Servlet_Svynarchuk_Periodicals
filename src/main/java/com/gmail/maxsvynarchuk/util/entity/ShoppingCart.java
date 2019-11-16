package com.gmail.maxsvynarchuk.util.entity;

import com.gmail.maxsvynarchuk.persistence.entity.Subscription;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents virtual shopping cart for storing items
 */
public class ShoppingCart {
    private final List<Subscription> items = new ArrayList<>();

    public boolean addItem(Subscription subscription) {
        Objects.requireNonNull(subscription);
        if (isInCart(subscription)) {
            return false;
        }
        return items.add(subscription);
    }

    public void removeItem(int cartItemId) {
        items.removeIf(item -> item.getPeriodical().getId() == cartItemId);
    }

    public void removeAll() {
        items.clear();
    }

    public List<Subscription> getItems() {
        return new ArrayList<>(items);
    }

    //TODO calculate expression
    public BigDecimal getTotalPrice() {
        BigDecimal totalValue = new BigDecimal(0)
                .setScale(2, RoundingMode.HALF_EVEN);
        for (Subscription subscription : items) {
            totalValue = totalValue.add(subscription.getPeriodical().getPrice());
        }
        return totalValue;
    }

    public boolean isInCart(Subscription subscriptionToCheck) {
        return items.contains(subscriptionToCheck);
    }

}
