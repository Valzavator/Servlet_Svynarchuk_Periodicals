package com.gmail.maxsvynarchuk.service.entity;

import com.gmail.maxsvynarchuk.persistence.entity.Subscription;
import com.gmail.maxsvynarchuk.provider.EntityProvider;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ShoppingCartTest {

    private ShoppingCart shoppingCart;

    @BeforeEach
    private void setUp() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    void addItemWithActivePeriodicalTest() {
        Subscription subscription = EntityProvider.getSubscriptionWithActivePeriodical();

        assertEquals(0, shoppingCart.size());
        assertTrue(shoppingCart.addItem(subscription));
        assertEquals(1, shoppingCart.size());
        assertFalse(shoppingCart.addItem(subscription));
        assertEquals(1, shoppingCart.size());
    }

    @Test
    void removeItemTest() {
        Subscription subscription = EntityProvider.getSubscriptionWithActivePeriodical();

        assertTrue(shoppingCart.addItem(subscription));
        assertEquals(1, shoppingCart.size());
        shoppingCart.removeItem(5);
        assertEquals(0, shoppingCart.size());
    }

    @Test
    void removeItemInvalidIdTest() {
        Subscription subscription = EntityProvider.getSubscriptionWithActivePeriodical();

        assertTrue(shoppingCart.addItem(subscription));
        assertEquals(1, shoppingCart.size());

        shoppingCart.removeItem(999);

        assertEquals(1, shoppingCart.size());
    }

    @Test
    void getTotalPriceTest() {
        List<Subscription> subscriptions =
                EntityProvider.getSubscriptionsWithActivePeriodicals();

        BigDecimal expectedTotalPrice = new BigDecimal("1");
        shoppingCart.addItem(subscriptions.get(0));
        assertEquals(0, expectedTotalPrice.compareTo(
                shoppingCart.getTotalPrice()));

        expectedTotalPrice = new BigDecimal("6.4");
        shoppingCart.addItem(subscriptions.get(1));
        assertEquals(0, expectedTotalPrice.compareTo(
                shoppingCart.getTotalPrice()));

        expectedTotalPrice = new BigDecimal("20.8");
        shoppingCart.addItem(subscriptions.get(2));
        assertEquals(0, expectedTotalPrice.compareTo(
                shoppingCart.getTotalPrice()));

        expectedTotalPrice = new BigDecimal("54.40");
        shoppingCart.addItem(subscriptions.get(3));
        assertEquals(0, expectedTotalPrice.compareTo(
                shoppingCart.getTotalPrice()));
    }

    @Test
    void isHasSuspendedPeriodicalTest() {
        List<Subscription> activeSubscriptions =
                EntityProvider.getSubscriptionsWithActivePeriodicals();
        Subscription suspendedSubscription =
                EntityProvider.getSubscriptionWithSuspendedPeriodical();

        shoppingCart.addItem(activeSubscriptions.get(0));
        shoppingCart.addItem(activeSubscriptions.get(1));
        shoppingCart.addItem(activeSubscriptions.get(2));
        shoppingCart.addItem(activeSubscriptions.get(3));
        assertFalse(shoppingCart.isHasSuspendedPeriodical());

        shoppingCart.addItem(suspendedSubscription);
        assertTrue(shoppingCart.isHasSuspendedPeriodical());
    }

    @Test
    void getItemsTest() {
        List<Subscription> activeSubscriptions =
                EntityProvider.getSubscriptionsWithActivePeriodicals();

        shoppingCart.addItem(activeSubscriptions.get(0));
        shoppingCart.addItem(activeSubscriptions.get(1));
        shoppingCart.addItem(activeSubscriptions.get(2));
        shoppingCart.addItem(activeSubscriptions.get(3));

        List<Subscription> actual = shoppingCart.getItems();

        assertNotSame(activeSubscriptions, actual);
        assertEquals(activeSubscriptions, actual);
    }

    @Test
    void updateItemWithSameItemTest() {
        Subscription subscription =
                EntityProvider.getSubscriptionWithActivePeriodical();
        Subscription updatedSubscription =
                EntityProvider.getSubscriptionWithActivePeriodical();
        updatedSubscription.getPeriodical().setStatus(PeriodicalStatus.SUSPENDED);

        shoppingCart.addItem(subscription);
        shoppingCart.updateItem(updatedSubscription);

        List<Subscription> expected = new ArrayList<Subscription>() {{
            add(updatedSubscription);
        }};
        List<Subscription> actual = shoppingCart.getItems();

        assertNotSame(subscription, updatedSubscription);
        assertNotSame(expected, actual);
        assertEquals(expected, actual);
    }

    @Test
    void updateItemWithAnotherItemTest() {
        Subscription subscription =
                EntityProvider.getSubscriptionWithActivePeriodical();
        Subscription updatedSubscription =
                EntityProvider.getSubscriptionWithSuspendedPeriodical();

        shoppingCart.addItem(subscription);
        shoppingCart.updateItem(updatedSubscription);

        List<Subscription> expected = new ArrayList<Subscription>() {{
            add(updatedSubscription);
        }};
        List<Subscription> actual = shoppingCart.getItems();

        assertNotSame(subscription, updatedSubscription);
        assertNotSame(expected, actual);
        assertNotEquals(expected, actual);
    }
}
