package com.gmail.maxsvynarchuk.presentation.command.impl.admin;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.PeriodicalIssue;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.exception.BadRequestException;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.presentation.util.mapper.RequestMapperFactory;
import com.gmail.maxsvynarchuk.presentation.util.validator.ValidatorManagerFactory;
import com.gmail.maxsvynarchuk.service.IssueService;
import com.gmail.maxsvynarchuk.service.PeriodicalService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

public class PostCreateIssueCommand implements Command {
    private static Logger LOGGER =
            LoggerFactory.getLogger(PostCreateIssueCommand.class);
    private final PeriodicalService periodicalService =
            ServiceFactory.getPeriodicalService();
    private final IssueService issueService =
            ServiceFactory.getIssueService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Start of new issue creation");
        Long periodicalId = Long.valueOf(
                request.getParameter(RequestParameters.PERIODICAL_ID));
        Optional<Periodical> periodicalOpt = periodicalService.findPeriodicalById(periodicalId);
        PeriodicalIssue periodicalIssueDTO = RequestMapperFactory.getCreateIssueMapper()
                .mapToObject(request);

        Map<String, Boolean> errors = ValidatorManagerFactory.getIssueValidator()
                .validate(periodicalIssueDTO);

        if (errors.isEmpty()) {
            if (!periodicalOpt.isPresent() ||
                    periodicalOpt.get().getStatus() == PeriodicalStatus.SUSPENDED) {
                LOGGER.debug("Periodical with id {} doesn't exist or has suspend status", periodicalId);
                throw new BadRequestException();
            }
            boolean isCreated = issueService.addIssueToPeriodical(
                    periodicalOpt.get(), periodicalIssueDTO);
            if (isCreated) {
                LOGGER.debug("Issue was successfully created");
                return CommandResult.redirect(PagesPaths.ADMIN_CATALOG_PATH);
            } else {
                LOGGER.debug("Issue with such number already exist");
                errors.put(Attributes.ERROR_ISSUE_EXIST, true);
            }
        }

        LOGGER.debug("Invalid creation parameters");
        request.setAttribute(Attributes.ERRORS, errors);
        request.setAttribute(Attributes.ISSUE_DTO, periodicalIssueDTO);

        LOGGER.debug("Issue creation fail");
        return CommandResult.forward(Views.CREATE_ISSUE_VIEW);
    }
}
