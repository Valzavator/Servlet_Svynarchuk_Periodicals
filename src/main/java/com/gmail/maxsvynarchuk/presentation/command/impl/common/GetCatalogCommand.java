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
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetCatalogCommand implements Command {
    private final static long RECORDS_PER_PAGE = 5;
    private final PeriodicalService periodicalService =
            ServiceFactory.getPeriodicalService();
    private final SubscriptionService subscriptionService =
            ServiceFactory.getSubscriptionService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        PaginationManager paginationManager = new PaginationManager(RECORDS_PER_PAGE);
        long rowsCount =
                periodicalService.getPeriodicalsCountByStatus(PeriodicalStatus.ACTIVE);
        long skip = paginationManager.manage(request, rowsCount);
        List<Periodical> periodicals =
                periodicalService.findAllPeriodicalsByStatus(PeriodicalStatus.ACTIVE,
                        skip,
                        paginationManager.getRecordsPerPage());
        request.setAttribute(Attributes.CATALOG, periodicals);
        if (!periodicals.isEmpty()) {
            List<SubscriptionPlan> subscriptionPlans =
                    subscriptionService.findAllSubscriptionPlans();
            request.setAttribute(Attributes.SUBSCRIPTION_PLANS, subscriptionPlans);
        }
        Util.checkErrorParameter(request, RequestParameters.ERROR_ATTRIBUTE);
        return CommandResult.forward(Views.CATALOG_VIEW);
    }
}
