package com.gmail.maxsvynarchuk.presentation.command.impl.admin;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.exception.BadRequestException;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.presentation.util.mapper.RequestMapperFactory;
import com.gmail.maxsvynarchuk.presentation.util.validator.ValidatorManagerFactory;
import com.gmail.maxsvynarchuk.service.PeriodicalService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

public class PostEditPeriodicalCommand implements Command {
    private static Logger LOGGER =
            LoggerFactory.getLogger(PostEditPeriodicalCommand.class);
    private final PeriodicalService periodicalService =
            ServiceFactory.getPeriodicalService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Start editing of periodical");
        Periodical periodicalDTO = RequestMapperFactory.getEditPeriodicalMapper()
                .mapToObject(request);

        Map<String, Boolean> errors = ValidatorManagerFactory.getPeriodicalValidator()
                .validate(periodicalDTO);

        if (errors.isEmpty()) {
            Optional<Periodical> periodicalOpt =
                    periodicalService.findPeriodicalById(periodicalDTO.getId());
            if (!periodicalOpt.isPresent() ||
                    periodicalOpt.get().getStatus() == PeriodicalStatus.SUSPENDED) {
                LOGGER.debug("Periodical with id {} doesn't exist or has suspend status",
                        periodicalDTO.getId());
                throw new BadRequestException();
            }
            periodicalService.updatePeriodical(periodicalDTO);
            LOGGER.debug("Periodical was successfully edit");
            return CommandResult.redirect(PagesPaths.ADMIN_CATALOG_PATH);
        }

        LOGGER.debug("Invalid editing parameters");
        request.setAttribute(Attributes.PERIODICAL_DTO, periodicalDTO);
        request.setAttribute(Attributes.ERRORS, errors);
        request.setAttribute(Attributes.PERIODICAL_TYPES, periodicalService.findAllPeriodicalTypes());
        request.setAttribute(Attributes.FREQUENCIES, periodicalService.findAllFrequencies());
        request.setAttribute(Attributes.PUBLISHERS, periodicalService.findAllPublishers());

        LOGGER.debug("Periodical editing failed");
        return CommandResult.forward(Views.EDIT_PERIODICAL_VIEW);
    }
}
