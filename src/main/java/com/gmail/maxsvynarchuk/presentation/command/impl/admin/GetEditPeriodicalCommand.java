package com.gmail.maxsvynarchuk.presentation.command.impl.admin;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.exception.BadRequestException;
import com.gmail.maxsvynarchuk.presentation.exception.NotFoundException;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.service.PeriodicalService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class GetEditPeriodicalCommand implements Command {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(GetEditPeriodicalCommand.class);
    private final PeriodicalService periodicalService =
            ServiceFactory.getPeriodicalService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Attempt to get page for editing periodical");
        Long periodicalId = Long.valueOf(
                request.getParameter(RequestParameters.PERIODICAL_ID));
        Optional<Periodical> periodicalOpt =
                periodicalService.findPeriodicalById(periodicalId);

        if (periodicalOpt.isPresent()) {
            Periodical periodicalDTO = periodicalOpt.get();
            if (periodicalDTO.getStatus() == PeriodicalStatus.SUSPENDED) {
                LOGGER.debug("Can't edit periodical with suspended status");
                throw new BadRequestException();
            }
            request.setAttribute(Attributes.PERIODICAL_DTO,
                    periodicalDTO);
            request.setAttribute(Attributes.PERIODICAL_TYPES,
                    periodicalService.findAllPeriodicalTypes());
            request.setAttribute(Attributes.FREQUENCIES,
                    periodicalService.findAllFrequencies());
            request.setAttribute(Attributes.PUBLISHERS,
                    periodicalService.findAllPublishers());
            LOGGER.debug("Attempt to get page for editing periodical is successful");
            return CommandResult.forward(Views.EDIT_PERIODICAL_VIEW);
        } else {
            LOGGER.debug("Periodical with id {} doesn't exist", periodicalId);
            throw new NotFoundException();
        }
    }
}
