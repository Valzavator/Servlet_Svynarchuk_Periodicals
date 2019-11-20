package com.gmail.maxsvynarchuk.presentation.command.impl.admin;

import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.service.PeriodicalService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCreatePeriodicalCommand implements Command {
    private final PeriodicalService periodicalService =
            ServiceFactory.getPeriodicalService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(Attributes.PERIODICAL_TYPES,
                periodicalService.findAllPeriodicalTypes());
        request.setAttribute(Attributes.FREQUENCIES,
                periodicalService.findAllFrequencies());
        request.setAttribute(Attributes.PUBLISHERS,
                periodicalService.findAllPublishers());

        return CommandResult.forward(Views.CREATE_PERIODICAL_VIEW);
    }
}
