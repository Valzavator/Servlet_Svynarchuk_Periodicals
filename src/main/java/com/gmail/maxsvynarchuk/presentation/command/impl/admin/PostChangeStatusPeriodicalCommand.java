package com.gmail.maxsvynarchuk.presentation.command.impl.admin;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.Util;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.service.PeriodicalService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class PostChangeStatusPeriodicalCommand implements Command {
    private static Logger LOGGER =
            LoggerFactory.getLogger(PostChangeStatusPeriodicalCommand.class);
    private final PeriodicalService periodicalService =
            ServiceFactory.getPeriodicalService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Start the process of changing status of the periodical");
        String referer = Util.getReferer(request, PagesPaths.ADMIN_CATALOG_PATH);
        Long periodicalId = Long.valueOf(
                request.getParameter(RequestParameters.PERIODICAL_ID));
        PeriodicalStatus newPeriodicalStatus = PeriodicalStatus.valueOf(
                request.getParameter(RequestParameters.PERIODICAL_STATUS).toUpperCase());
        Optional<Periodical> periodicalOpt =
                periodicalService.findPeriodicalById(periodicalId);
        if (periodicalOpt.isPresent()) {
            periodicalService.changeStatus(periodicalOpt.get(), newPeriodicalStatus);
            LOGGER.info("Status of the periodical was successfully changed");
        } else {
            LOGGER.debug("Periodical with id {} doesn't exist. " +
                    "Changing status of the periodical failed", periodicalId);
        }
        return CommandResult.redirect(referer);
    }
}
