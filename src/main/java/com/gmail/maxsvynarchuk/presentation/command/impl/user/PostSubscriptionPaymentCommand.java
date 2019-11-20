package com.gmail.maxsvynarchuk.presentation.command.impl.user;

import com.gmail.maxsvynarchuk.persistence.entity.Subscription;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.Util;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.service.ShoppingCartService;
import com.gmail.maxsvynarchuk.service.SubscriptionService;
import com.gmail.maxsvynarchuk.service.entity.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

public class PostSubscriptionPaymentCommand implements Command {
    private final SubscriptionService subscriptionService = ServiceFactory.getSubscriptionService();
    private final ShoppingCartService shoppingCartService = ServiceFactory.getShoppingCartService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        User user = Util.getAuthorizedUser(request.getSession());
        ShoppingCart shoppingCart = Util.getShoppingCart(request.getSession());
        List<Subscription> subscriptions = shoppingCart.getItems();
        BigDecimal totalPrice = shoppingCart.getTotalPrice();

        if (shoppingCart.size() == 0 || shoppingCart.isHasSuspendedPeriodical()) {
            return CommandResult.redirect(PagesPaths.CART_PATH);
        }

        subscriptionService.processSubscriptions(user, totalPrice, subscriptions);
        shoppingCartService.removeAllItemFromCart(shoppingCart);
        return CommandResult.redirect(PagesPaths.CART_PATH);
    }
}
