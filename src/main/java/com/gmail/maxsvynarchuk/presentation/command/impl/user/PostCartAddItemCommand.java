package com.gmail.maxsvynarchuk.presentation.command.impl.user;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.SubscriptionPlan;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.Util;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.service.PeriodicalService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.service.ShoppingCartService;
import com.gmail.maxsvynarchuk.service.SubscriptionService;
import com.gmail.maxsvynarchuk.service.entity.ShoppingCart;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class PostCartAddItemCommand implements Command {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(PostCartAddItemCommand.class);
    private final PeriodicalService periodicalService = ServiceFactory.getPeriodicalService();
    private final SubscriptionService subscriptionService = ServiceFactory.getSubscriptionService();
    private final ShoppingCartService shoppingCartService = ServiceFactory.getShoppingCartService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Attempt to add item to shopping cart");
        String referer = Util.getReferer(request);
        referer = Util.removeParameterFromURI(referer, RequestParameters.ERROR_ATTRIBUTE);

        User user = Util.getAuthorizedUser(request.getSession());
        Long periodicalId = Long.valueOf(
                request.getParameter(RequestParameters.PERIODICAL_ID));
        Integer subscriptionPlanId = Integer.valueOf(
                request.getParameter(RequestParameters.SUBSCRIPTION_PLAN_ID));
        Optional<Periodical> periodicalOpt =
                periodicalService.findPeriodicalById(periodicalId);
        SubscriptionPlan subscriptionPlan =
                subscriptionService.findSubscriptionPlanById(subscriptionPlanId)
                        .orElseThrow(IllegalArgumentException::new);

        if (!periodicalOpt.isPresent() ||
                periodicalOpt.get().getStatus() == PeriodicalStatus.SUSPENDED) {
            referer = Util.addParameterToURI(referer,
                    RequestParameters.ERROR_ATTRIBUTE,
                    Attributes.ERROR_PERIODICAL_INVALID);
            LOGGER.debug("Invalid periodical with id {} for subscription", periodicalId);
            return CommandResult.redirect(referer);
        }

        Periodical periodical = periodicalOpt.get();
        boolean isAlreadySubscribed =
                subscriptionService.isAlreadySubscribed(user, periodical);
        if (isAlreadySubscribed) {
            referer = Util.addParameterToURI(referer,
                    RequestParameters.ERROR_ATTRIBUTE,
                    Attributes.ERROR_IS_ALREADY_SUBSCRIBED);
            LOGGER.debug("User is already subscribed to periodical with id {}", periodicalId);
            return CommandResult.redirect(referer);
        }

        ShoppingCart shoppingCart = Util.getShoppingCart(request.getSession());
        boolean isAddedToCart = shoppingCartService.addItemToCart(shoppingCart, user,
                periodical, subscriptionPlan);
        if (!isAddedToCart) {
            referer = Util.addParameterToURI(referer,
                    RequestParameters.ERROR_ATTRIBUTE,
                    Attributes.ERROR_IS_ALREADY_IN_CART);
            LOGGER.debug("Item already exists in cart");
        } else {
            LOGGER.debug("Item successfully added to cart");
        }

        return CommandResult.redirect(referer);
    }
}
