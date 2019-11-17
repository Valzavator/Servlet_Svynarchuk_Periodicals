package com.gmail.maxsvynarchuk.presentation.command.impl.user;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.Subscription;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.PaginationManager;
import com.gmail.maxsvynarchuk.presentation.util.Util;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetSubscriptionsCommand implements Command {
    private final SubscriptionService subscriptionService = ServiceFactory.getSubscriptionService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        User user = Util.getAuthorizedUser(request.getSession());
        long rowsCount = subscriptionService.getActiveSubscriptionsCountByUser(user);
        long skip = PaginationManager.manage(request, rowsCount);
        List<Subscription> subscriptions = subscriptionService.findAllActiveSubscriptionsByUser(user,
                skip, PaginationManager.DEFAULT_RECORDS_PER_PAGE);
        request.setAttribute(Attributes.SUBSCRIPTIONS, subscriptions);
        return CommandResult.forward(Views.SUBSCRIPTIONS_VIEW);
    }
}
