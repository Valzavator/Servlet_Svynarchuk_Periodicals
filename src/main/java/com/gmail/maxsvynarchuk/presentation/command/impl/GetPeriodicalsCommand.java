package com.gmail.maxsvynarchuk.presentation.command.impl;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.SubscriptionPlan;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.command.impl.authorization.PostSignInCommand;
import com.gmail.maxsvynarchuk.presentation.util.PaginationManager;
import com.gmail.maxsvynarchuk.presentation.util.Util;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.service.PeriodicalService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.service.SubscriptionPlanService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public class GetPeriodicalsCommand implements Command {
    private final PeriodicalService periodicalService = ServiceFactory.getPeriodicalService();
    private final SubscriptionPlanService subscriptionPlanService = ServiceFactory.getSubscriptionPlanService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        long rowsCount = periodicalService.getPeriodicalsCount();
        long skip = PaginationManager.manage(request, rowsCount);
        List<Periodical> periodicals = periodicalService.getPeriodicals(skip, PaginationManager.RECORDS_PER_PAGE);
        request.setAttribute(Attributes.PERIODICALS, periodicals);
        if (!periodicals.isEmpty()) {
            List<SubscriptionPlan> subscriptionPlans = subscriptionPlanService.getAllSubscriptionPlans();
            request.setAttribute(Attributes.SUBSCRIPTION_PLANS, subscriptionPlans);
        }
        String error = request.getParameter(RequestParameters.ERROR_ATTRIBUTE);
        if (Objects.nonNull(error) && !error.isEmpty()) {
            request.setAttribute(error, true);
        }
        return CommandResult.forward(Views.CATALOG_VIEW);
    }
}
