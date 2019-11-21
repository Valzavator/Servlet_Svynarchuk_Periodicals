package com.gmail.maxsvynarchuk.service.entity;

import com.gmail.maxsvynarchuk.persistence.entity.Subscription;
import com.gmail.maxsvynarchuk.provider.EntityProvider;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ShoppingCartTest {

    private static List<Subscription> activeSubscriptions;
    private static Subscription updatedSubscription;
    private static Subscription suspendedSubscription;

    private ShoppingCart shoppingCart;

    //TODO - change
    @BeforeAll
    private static void initSubscriptions() {
        activeSubscriptions = Collections.unmodifiableList(new ArrayList<Subscription>() {{
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

        suspendedSubscription = EntityProvider.getSubscriptionForCard(
                EntityProvider.getPeriodical(5L, PeriodicalStatus.SUSPENDED, "10"),
                EntityProvider.getTwelveMonthSubscriptionPlan());

    }

    @BeforeEach
    private void initShoppingCart() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    void addItemWithActivePeriodicalTest() {
        assertEquals(0, shoppingCart.size());
        assertTrue(shoppingCart.addItem(activeSubscriptions.get(0)));
        assertEquals(1, shoppingCart.size());
        assertFalse(shoppingCart.addItem(activeSubscriptions.get(0)));
    }

    @Test
    void removeItemTest() {
        assertTrue(shoppingCart.addItem(activeSubscriptions.get(0)));
        assertEquals(1, shoppingCart.size());

        shoppingCart.removeItem(1);

        assertEquals(0, shoppingCart.size());
    }

    @Test
    void removeItemInvalidIdTest() {
        assertTrue(shoppingCart.addItem(activeSubscriptions.get(0)));
        assertEquals(1, shoppingCart.size());

        shoppingCart.removeItem(999);

        assertEquals(1, shoppingCart.size());
    }

    @Test
    void getTotalPriceTest() {
        BigDecimal expectedTotalPrice = new BigDecimal("1");
        shoppingCart.addItem(activeSubscriptions.get(0));
        assertEquals(0, expectedTotalPrice.compareTo(
                shoppingCart.getTotalPrice()));

        expectedTotalPrice = new BigDecimal("6.4");
        shoppingCart.addItem(activeSubscriptions.get(1));
        assertEquals(0, expectedTotalPrice.compareTo(
                shoppingCart.getTotalPrice()));

        expectedTotalPrice = new BigDecimal("20.8");
        shoppingCart.addItem(activeSubscriptions.get(2));
        assertEquals(0, expectedTotalPrice.compareTo(
                shoppingCart.getTotalPrice()));

        expectedTotalPrice = new BigDecimal("54.40");
        shoppingCart.addItem(activeSubscriptions.get(3));
        assertEquals(0, expectedTotalPrice.compareTo(
                shoppingCart.getTotalPrice()));
    }

    @Test
    void isHasSuspendedPeriodicalTest() {
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
        shoppingCart.addItem(activeSubscriptions.get(0));
        shoppingCart.addItem(activeSubscriptions.get(1));
        shoppingCart.addItem(activeSubscriptions.get(2));
        shoppingCart.addItem(activeSubscriptions.get(3));

        assertEquals(activeSubscriptions, shoppingCart.getItems());
    }

    @Test
    void updateItemWithSameItemTest() {
        Subscription subscription = EntityProvider.getSubscriptionForCard(
                EntityProvider.getPeriodical(1L, PeriodicalStatus.ACTIVE, "1"),
                EntityProvider.getOneMonthSubscriptionPlan());
        Subscription updatedSubscription = EntityProvider.getSubscriptionForCard(
                EntityProvider.getPeriodical(1L, PeriodicalStatus.SUSPENDED, "1"),
                EntityProvider.getOneMonthSubscriptionPlan());

        shoppingCart.addItem(subscription);
        shoppingCart.updateItem(updatedSubscription);

        List<Subscription> expected = new ArrayList<Subscription>() {{
            add(updatedSubscription);
        }};
        assertEquals(expected, shoppingCart.getItems());
    }

    @Test
    void updateItemWithAnotherItemTest() {
        Subscription subscription = activeSubscriptions.get(0);
        Subscription updatedSubscription = activeSubscriptions.get(1);

        shoppingCart.addItem(subscription);
        shoppingCart.updateItem(updatedSubscription);

        List<Subscription> expected = new ArrayList<Subscription>() {{
            add(updatedSubscription);
        }};
        assertNotEquals(expected, shoppingCart.getItems());
    }
}
