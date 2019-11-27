package com.gmail.maxsvynarchuk.presentation.command.impl.common;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.PeriodicalIssue;
import com.gmail.maxsvynarchuk.persistence.entity.SubscriptionPlan;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.exception.NotFoundException;
import com.gmail.maxsvynarchuk.presentation.util.Util;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.service.IssueService;
import com.gmail.maxsvynarchuk.service.PeriodicalService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class GetPeriodicalCommand implements Command {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(GetPeriodicalCommand.class);
    private final PeriodicalService periodicalService = ServiceFactory.getPeriodicalService();
    private final SubscriptionService subscriptionService = ServiceFactory.getSubscriptionService();
    private final IssueService issueService = ServiceFactory.getIssueService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Attempt to get page for overview periodical");
        Long periodicalId = Long.valueOf(
                request.getParameter(RequestParameters.PERIODICAL_ID));
        Optional<Periodical> periodicalOpt =
                periodicalService.findPeriodicalById(periodicalId);
        if (periodicalOpt.isPresent()) {
            List<SubscriptionPlan> subscriptionPlans =
                    subscriptionService.findAllSubscriptionPlans();
            List<PeriodicalIssue> periodicalIssues =
                    issueService.findAllIssuesByPeriodical(periodicalOpt.get());
            request.setAttribute(Attributes.SUBSCRIPTION_PLANS, subscriptionPlans);
            request.setAttribute(Attributes.PERIODICAL, periodicalOpt.get());
            request.setAttribute(Attributes.ISSUES, periodicalIssues);
            Util.checkErrorParameter(request, RequestParameters.ERROR_ATTRIBUTE);
            LOGGER.debug("Attempt to get page for overview periodical is successful");
            return CommandResult.forward(Views.PERIODICAL_VIEW);
        } else {
            LOGGER.debug("Periodical with id {} doesn't exist", periodicalId);
            throw new NotFoundException();
        }
    }
}
