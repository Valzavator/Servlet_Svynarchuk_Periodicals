package com.gmail.maxsvynarchuk.presentation.command.impl;

import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.Util;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostCartAddItemCommand implements Command {
    private final SubscriptionService subscriptionService = ServiceFactory.getSubscriptionService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        if (!Util.isAlreadyLoggedIn(request.getSession())) {
            return CommandResult.redirect(PagesPaths.SIGN_IN_PATH);
        }
        User user = Util.getAuthorizedUser(request.getSession());
        Long periodicalId = Long.valueOf(request.getParameter(RequestParameters.PERIODICAL_ID));
        Long subscriptionPlanId = Long.valueOf(request.getParameter(RequestParameters.SUBSCRIPTION_PLAN_ID));

        System.out.println(request.getHeader("referer"));
        System.out.println(periodicalId);
        System.out.println(subscriptionPlanId);

        boolean isAlreadySubscribed =
                subscriptionService.isAlreadySubscribed(user.getId(), periodicalId);
        if (!isAlreadySubscribed) {
//            Cart cart = getCartFromSession(session);
//            cartService.addItemToCart(cart, itemIdValue);
        } else {
            request.setAttribute(Attributes.ERROR_IS_ALREADY_SUBSCRIBED, true);
        }

        return CommandResult.forward(Views.CATALOG_VIEW);
    }
}
