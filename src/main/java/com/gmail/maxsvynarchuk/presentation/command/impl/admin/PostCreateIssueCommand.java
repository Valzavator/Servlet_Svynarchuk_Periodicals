package com.gmail.maxsvynarchuk.presentation.command.impl.admin;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.PeriodicalIssue;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.presentation.util.mapper.RequestMapperFactory;
import com.gmail.maxsvynarchuk.presentation.util.validator.ValidatorManager;
import com.gmail.maxsvynarchuk.service.PeriodicalService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

public class PostCreateIssueCommand implements Command {
    private static Logger LOGGER = LoggerFactory.getLogger(PostCreateIssueCommand.class);
    private final PeriodicalService periodicalService = ServiceFactory.getPeriodicalService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Start of new issue creation");
        PeriodicalIssue periodicalIssueDTO;
        Optional<Periodical> periodicalOpt;
        Long periodicalId;
        try {
            periodicalId = Long.valueOf(
                    request.getParameter(RequestParameters.PERIODICAL_ID));
            periodicalOpt = periodicalService.findPeriodicalById(periodicalId);
            periodicalIssueDTO = RequestMapperFactory.getCreateIssueMapper()
                    .mapToObject(request);
        } catch (NumberFormatException e) {
            LOGGER.info("Invalid parameters of request", e);
            throw e;
        }

        Map<String, Boolean> errors = ValidatorManager
                .validateIssueParameters(periodicalIssueDTO);

        if (errors.isEmpty()) {
            if (periodicalOpt.isPresent()) {
                System.out.println(periodicalIssueDTO);
                // TODO isPresent
                boolean isPresent = periodicalService.addIssueToPeriodical(
                        periodicalOpt.get(), periodicalIssueDTO);
                LOGGER.info("Issue was successfully create");
            } else {
                LOGGER.info("Periodical with id {} doesn't exist", periodicalId);
            }
            return CommandResult.redirect(PagesPaths.ADMIN_CATALOG_PATH);
        }

        LOGGER.info("Invalid creation parameters");
        request.setAttribute(Attributes.ERRORS, errors);
        request.setAttribute(Attributes.ISSUE_DTO, periodicalIssueDTO);

        LOGGER.info("Issue creation fail");
        return CommandResult.forward(Views.CREATE_ISSUE_VIEW);
    }
}
