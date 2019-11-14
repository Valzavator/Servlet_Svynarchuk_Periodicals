package com.gmail.maxsvynarchuk.presentation.command.impl.common;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.SubscriptionPlan;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.PaginationManager;
import com.gmail.maxsvynarchuk.presentation.util.Util;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.service.PeriodicalService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetCatalogCommand implements Command {
    private final PeriodicalService periodicalService = ServiceFactory.getPeriodicalService();
    private final SubscriptionService subscriptionService = ServiceFactory.getSubscriptionService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        long rowsCount = periodicalService.getPeriodicalsCount();
        long skip = PaginationManager.manage(
                request, rowsCount, PaginationManager.CATALOG_RECORDS_PER_PAGE);
        List<Periodical> periodicals = periodicalService.findAllPeriodicals(
                skip, PaginationManager.CATALOG_RECORDS_PER_PAGE);
        request.setAttribute(Attributes.CATALOG, periodicals);
        if (!periodicals.isEmpty()) {
            List<SubscriptionPlan> subscriptionPlans = subscriptionService.getAllSubscriptionPlans();
            request.setAttribute(Attributes.SUBSCRIPTION_PLANS, subscriptionPlans);
        }
        Util.checkErrorParameter(request, RequestParameters.ERROR_ATTRIBUTE);
        return CommandResult.forward(Views.CATALOG_VIEW);
    }
}
