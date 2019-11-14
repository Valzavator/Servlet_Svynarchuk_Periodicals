package com.gmail.maxsvynarchuk.presentation.command.impl.admin;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.SubscriptionPlan;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.Util;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.service.PeriodicalService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class GetEditPeriodicalCommand implements Command {
    private final PeriodicalService periodicalService = ServiceFactory.getPeriodicalService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        Long periodicalId = Long.valueOf(request.getParameter(RequestParameters.PERIODICAL_ID));
        Optional<Periodical> periodicalOpt = periodicalService.findPeriodicalById(periodicalId);

        if (periodicalOpt.isPresent()) {
            request.setAttribute(Attributes.PERIODICAL_DTO, periodicalOpt.get());
            request.setAttribute(Attributes.PERIODICAL_TYPES, periodicalService.findAllPeriodicalTypes());
            request.setAttribute(Attributes.FREQUENCIES, periodicalService.findAllFrequencies());
            request.setAttribute(Attributes.PUBLISHERS, periodicalService.findAllPublishers());
            return CommandResult.forward(Views.EDIT_PERIODICAL_VIEW);
        } else {
            return CommandResult.forward(Views.ERROR_404_VIEW);
        }
    }
}
