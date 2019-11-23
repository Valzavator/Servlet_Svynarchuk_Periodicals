package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.Subscription;
import com.gmail.maxsvynarchuk.persistence.entity.SubscriptionPlan;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.service.entity.ShoppingCart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

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
    private PeriodicalService periodicalService = ServiceFactory.getPeriodicalService();

    private ShoppingCartService() {
    }

    private static class Singleton {
        private final static ShoppingCartService INSTANCE = new ShoppingCartService();
    }

    public static ShoppingCartService getInstance() {
        return ShoppingCartService.Singleton.INSTANCE;
    }

    public boolean addItemToCart(ShoppingCart shoppingCart,
                                 User user,
                                 Periodical periodical,
                                 SubscriptionPlan subscriptionPlan) {
        LOGGER.debug("Attempt to add item to cart");
        Subscription subscription = Subscription.newBuilder()
                .setUser(user)
                .setPeriodical(periodical)
                .setSubscriptionPlan(subscriptionPlan)
                .build();
        return shoppingCart.addItem(subscription);
    }

    public void removeItemFromCart(ShoppingCart shoppingCart, Long cartItemId) {
        LOGGER.debug("Attempt to remove item from cart");
        shoppingCart.removeItem(cartItemId);
    }

    public void removeAllItemFromCart(ShoppingCart shoppingCart) {
        LOGGER.debug("Attempt to remove all item from cart");
        shoppingCart.removeAll();
    }

    public void updateShoppingCartItemsFromDatabase(ShoppingCart shoppingCart) {
        for (Subscription subscription : shoppingCart.getItems()) {
            Optional<Periodical> periodicalOpt =
                    periodicalService.findPeriodicalById(subscription.getPeriodical().getId());
            if (periodicalOpt.isPresent()) {
                subscription.setPeriodical(periodicalOpt.get());
                shoppingCart.updateItem(subscription);
            } else {
                LOGGER.error("A subscription cannot refer to a non-existent periodical");
                throw new ServiceException("A subscription cannot refer to a non-existent periodical");
            }
        }
    }
}
