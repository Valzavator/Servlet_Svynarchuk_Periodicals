package com.gmail.maxsvynarchuk.presentation.command.impl.user;

import com.gmail.maxsvynarchuk.persistence.entity.Subscription;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.Util;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.service.exception.ServiceException;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.service.ShoppingCartService;
import com.gmail.maxsvynarchuk.service.SubscriptionService;
import com.gmail.maxsvynarchuk.service.entity.ShoppingCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

public class PostSubscriptionPaymentCommand implements Command {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(PostSubscriptionPaymentCommand.class);
    private final SubscriptionService subscriptionService = ServiceFactory.getSubscriptionService();
    private final ShoppingCartService shoppingCartService = ServiceFactory.getShoppingCartService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Attempt to process new subscriptions");
        ShoppingCart shoppingCart = Util.getShoppingCart(request.getSession());
        shoppingCartService.updateShoppingCartItemsFromDatabase(shoppingCart);

        User user = Util.getAuthorizedUser(request.getSession());
        List<Subscription> subscriptions = shoppingCart.getItems();
        BigDecimal totalPrice = shoppingCart.getTotalPrice();

        try {
            subscriptionService.processSubscriptions(user, subscriptions, totalPrice);
        } catch (ServiceException exception) {
            LOGGER.error(exception.getMessage());
            return CommandResult.redirect(PagesPaths.CART_PATH);
        }

        shoppingCartService.removeAllItemFromCart(shoppingCart);
        LOGGER.debug("New subscriptions processed successfully");
        return CommandResult.redirect(PagesPaths.CART_PATH);
    }
}
