package com.gmail.maxsvynarchuk.presentation.command.impl.user;

import com.gmail.maxsvynarchuk.persistence.entity.Subscription;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.PaginationManager;
import com.gmail.maxsvynarchuk.presentation.util.Util;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public class GetSubscriptionsCommand implements Command {
    private final SubscriptionService subscriptionService = ServiceFactory.getSubscriptionService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        PaginationManager activeSubscriptionsPagination = new PaginationManager(5,
                RequestParameters.PAGINATION_ACTIVE_SUBSCRIPTIONS_PAGE,
                Attributes.PAGINATION_ACTIVE_SUBSCRIPTIONS_PAGE,
                Attributes.PAGINATION_ACTIVE_SUBSCRIPTIONS_NUMBER_OF_PAGES);
        PaginationManager expiredSubscriptionsPagination = new PaginationManager(5,
                RequestParameters.PAGINATION_EXPIRED_SUBSCRIPTIONS_PAGE,
                Attributes.PAGINATION_EXPIRED_SUBSCRIPTIONS_PAGE,
                Attributes.PAGINATION_EXPIRED_SUBSCRIPTIONS_NUMBER_OF_PAGES);
        String pill = request.getParameter(RequestParameters.PAGINATION_PILL);
        if (Objects.nonNull(pill)) {
            request.setAttribute(pill, true);
        }

        User user = Util.getAuthorizedUser(request.getSession());
        long activeRowsCount = subscriptionService.getSubscriptionsCountByUserAndStatus(user, false);
        long expiredRowsCount = subscriptionService.getSubscriptionsCountByUserAndStatus(user, true);
        long activeSkip = activeSubscriptionsPagination.manage(request, activeRowsCount);
        long expiredSkip = expiredSubscriptionsPagination.manage(request, expiredRowsCount);

        List<Subscription> activeSubscriptions =
                subscriptionService.findAllSubscriptionsByUserAndStatus(user,
                        false,
                        activeSkip,
                        activeSubscriptionsPagination.getRecordsPerPage());
        List<Subscription> expiredSubscriptions =
                subscriptionService.findAllSubscriptionsByUserAndStatus(user,
                        true,
                        expiredSkip,
                        expiredSubscriptionsPagination.getRecordsPerPage());

        request.setAttribute(Attributes.ACTIVE_SUBSCRIPTIONS, activeSubscriptions);
        request.setAttribute(Attributes.EXPIRED_SUBSCRIPTIONS, expiredSubscriptions);
        return CommandResult.forward(Views.SUBSCRIPTIONS_VIEW);
    }
}
