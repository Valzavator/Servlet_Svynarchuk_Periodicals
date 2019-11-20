package com.gmail.maxsvynarchuk.presentation.command.impl.admin;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.service.PeriodicalService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class GetCreateIssueCommand implements Command {
    private final PeriodicalService periodicalService =
            ServiceFactory.getPeriodicalService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        Long periodicalId = Long.valueOf(
                request.getParameter(RequestParameters.PERIODICAL_ID));
        Optional<Periodical> periodicalOpt =
                periodicalService.findPeriodicalById(periodicalId);
        if (periodicalOpt.isPresent()) {
            Periodical periodicalDTO = periodicalOpt.get();
            if (periodicalDTO.getStatus() == PeriodicalStatus.SUSPENDED) {
                return CommandResult.redirect(PagesPaths.ADMIN_CATALOG_PATH);
            }
            request.setAttribute(Attributes.PERIODICAL_DTO, periodicalDTO);
            return CommandResult.forward(Views.CREATE_ISSUE_VIEW);
        } else {
            return CommandResult.forward(Views.ERROR_404_VIEW);
        }
    }
}
