package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.Subscription;
import com.gmail.maxsvynarchuk.persistence.entity.SubscriptionPlan;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.service.entity.ShoppingCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Service responsible for processing some actions
 * that has to do with shopping cart
 *
 * @author Maksym Svynarhcuk
 * @see ShoppingCart
 */
public class ShoppingCartService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ShoppingCartService.class);
    private ShoppingCartService() {
    }

    private static class Singleton {
        private final static ShoppingCartService INSTANCE = new ShoppingCartService();
    }

    public static ShoppingCartService getInstance() {
        return ShoppingCartService.Singleton.INSTANCE;
    }

    public boolean addItemToCart(User user,
                                 ShoppingCart shoppingCart,
                                 Periodical periodical,
                                 SubscriptionPlan subscriptionPlan) {
        LOGGER.debug("Attempt to add item to cart");
        Objects.requireNonNull(user);
        Objects.requireNonNull(periodical);
        Objects.requireNonNull(subscriptionPlan);

        Subscription subscription = Subscription.newBuilder()
                .setUser(user)
                .setPeriodical(periodical)
                .setSubscriptionPlan(subscriptionPlan)
                .build();

        return shoppingCart.addItem(subscription);
    }

    /**
     * @param cartItemId id of item that needed to be removed from cart
     */
    public void removeItemFromCart(ShoppingCart shoppingCart, Integer cartItemId) {
        LOGGER.debug("Attempt to remove item from cart");
        shoppingCart.removeItem(cartItemId);
    }

    public void removeAllItemFromCart(ShoppingCart shoppingCart) {
        LOGGER.debug("Attempt to remove all item from cart");
        shoppingCart.removeAll();
    }
}
