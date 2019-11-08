package com.gmail.maxsvynarchuk.presentation.command.impl;

import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.command.impl.authorization.PostSignInCommand;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.service.PeriodicalService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.service.SubscriptionPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetPeriodicalsCommand implements Command {
    private static Logger LOGGER = LoggerFactory.getLogger(PostSignInCommand.class);
    private final PeriodicalService periodicalService = ServiceFactory.getPeriodicalService();
    private final SubscriptionPlanService subscriptionPlanService = ServiceFactory.getSubscriptionPlanService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("periodicals", periodicalService.getAllPeriodicals());
        request.setAttribute("subscriptionPlans", subscriptionPlanService.getAllSubscriptionPlans());

        return CommandResult.forward(Views.CATALOG_VIEW);
    }
}
