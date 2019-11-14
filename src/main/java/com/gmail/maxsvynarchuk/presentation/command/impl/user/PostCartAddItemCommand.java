package com.gmail.maxsvynarchuk.presentation.command.impl.user;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.Util;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.service.PeriodicalService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.service.SubscriptionService;
import com.gmail.maxsvynarchuk.util.PeriodicalStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class PostCartAddItemCommand implements Command {
    private final PeriodicalService periodicalService = ServiceFactory.getPeriodicalService();
    private final SubscriptionService subscriptionService = ServiceFactory.getSubscriptionService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String referer = Util.getReferer(request);

        User user = Util.getAuthorizedUser(request.getSession());
        Long periodicalId = Long.valueOf(request.getParameter(RequestParameters.PERIODICAL_ID));
        Long subscriptionPlanId = Long.valueOf(request.getParameter(RequestParameters.SUBSCRIPTION_PLAN_ID));

        Optional<Periodical> periodicalOpt = periodicalService.findPeriodicalById(periodicalId);
        if (!periodicalOpt.isPresent() ||
                periodicalOpt.get().getStatus() == PeriodicalStatus.SUSPENDED) {
            return CommandResult.redirect(referer);
        }

        boolean isAlreadySubscribed =
                subscriptionService.isAlreadySubscribed(user.getId(), periodicalId);
        if (!isAlreadySubscribed) {
//            Cart cart = getCartFromSession(session);
//            cartService.addItemToCart(cart, itemIdValue);
        } else {
            referer = Util.addParameterToURI(referer,
                    RequestParameters.ERROR_ATTRIBUTE, Attributes.ERROR_IS_ALREADY_SUBSCRIBED);
            return CommandResult.redirect(referer);
        }

        return CommandResult.forward(Views.CATALOG_VIEW);
    }
}
