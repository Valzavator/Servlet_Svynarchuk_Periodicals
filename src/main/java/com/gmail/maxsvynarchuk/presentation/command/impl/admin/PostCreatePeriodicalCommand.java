package com.gmail.maxsvynarchuk.presentation.command.impl.admin;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.presentation.util.mapper.RequestMapperFactory;
import com.gmail.maxsvynarchuk.presentation.util.validator.ValidatorManagerFactory;
import com.gmail.maxsvynarchuk.service.PeriodicalService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class PostCreatePeriodicalCommand implements Command {
    private static Logger LOGGER =
            LoggerFactory.getLogger(PostCreatePeriodicalCommand.class);
    private final PeriodicalService periodicalService =
            ServiceFactory.getPeriodicalService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Start of new periodical creation");
        Periodical periodicalDTO = RequestMapperFactory.getCreatePeriodicalMapper()
                .mapToObject(request);

        Map<String, Boolean> errors = ValidatorManagerFactory.getPeriodicalValidator()
                .validate(periodicalDTO);

        if (errors.isEmpty()) {
            periodicalService.createPeriodical(periodicalDTO);
            LOGGER.debug("Periodical was successfully create");
            return CommandResult.redirect(PagesPaths.ADMIN_CATALOG_PATH);
        }

        LOGGER.debug("Invalid creation parameters");
        request.setAttribute(Attributes.ERRORS, errors);
        request.setAttribute(Attributes.PERIODICAL_DTO, periodicalDTO);
        request.setAttribute(Attributes.PERIODICAL_TYPES, periodicalService.findAllPeriodicalTypes());
        request.setAttribute(Attributes.FREQUENCIES, periodicalService.findAllFrequencies());
        request.setAttribute(Attributes.PUBLISHERS, periodicalService.findAllPublishers());

        LOGGER.debug("Periodical creation fail");
        return CommandResult.forward(Views.CREATE_PERIODICAL_VIEW);
    }
}
