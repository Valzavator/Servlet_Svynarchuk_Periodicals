package com.gmail.maxsvynarchuk.presentation.command.impl.admin;

import com.gmail.maxsvynarchuk.persistence.entity.Frequency;
import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.PeriodicalType;
import com.gmail.maxsvynarchuk.persistence.entity.Publisher;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.command.impl.authorization.PostSignInCommand;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.presentation.util.validator.ValidatorManager;
import com.gmail.maxsvynarchuk.service.PeriodicalService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class PostEditPeriodicalCommand implements Command {
    private static Logger LOGGER = LoggerFactory.getLogger(PostEditPeriodicalCommand.class);
    private final PeriodicalService periodicalService = ServiceFactory.getPeriodicalService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Start editing of periodical");

        Long periodicalId = Long.valueOf(
                request.getParameter(RequestParameters.PERIODICAL_ID));
        Integer periodicalTypeId = Integer.valueOf(
                request.getParameter(RequestParameters.PERIODICAL_TYPE_ID));
        Integer periodicalFrequencyId = Integer.valueOf(
                request.getParameter(RequestParameters.PERIODICAL_FREQUENCY_ID));
        Long periodicalPublisherId = Long.valueOf(
                request.getParameter(RequestParameters.PERIODICAL_PUBLISHER_ID));

        BigDecimal price;
        PeriodicalType periodicalType;
        Frequency frequency;
        Publisher publisher;

        try {
            price =new BigDecimal(
                    request.getParameter(RequestParameters.PERIODICAL_PRICE));
            periodicalType = periodicalService.findPeriodicalTypeById(periodicalTypeId)
                    .orElseThrow(IllegalArgumentException::new);
            frequency = periodicalService.findFrequencyById(periodicalFrequencyId)
                    .orElseThrow(IllegalArgumentException::new);
            publisher = periodicalService.findPublisherById(periodicalPublisherId)
                    .orElseThrow(IllegalArgumentException::new);
        } catch (IllegalArgumentException e) {
            LOGGER.info("Invalid parameters");
            throw e;
        }

        Periodical periodicalDTO = Periodical.newBuilder()
                .setId(periodicalId)
                .setName(request.getParameter(RequestParameters.PERIODICAL_NAME))
                .setDescription(request.getParameter(RequestParameters.PERIODICAL_DESCRIPTION))
                .setPrice(price)
                .setPeriodicalType(periodicalType)
                .setFrequency(frequency)
                .setPublisher(publisher)
                .build();

        Map<String, Boolean> errors = ValidatorManager
                .validatePeriodicalParameters(periodicalDTO);

        if (errors.isEmpty()) {
            periodicalService.updatePeriodical(periodicalDTO);
            LOGGER.info("Periodical was successfully edit");
            return CommandResult.redirect(PagesPaths.ADMIN_CATALOG_PATH);
        }

        LOGGER.info("Invalid creation parameters");
        request.setAttribute(Attributes.PERIODICAL_DTO, periodicalDTO);
        request.setAttribute(Attributes.ERRORS, errors);
        request.setAttribute(Attributes.PERIODICAL_TYPES, periodicalService.findAllPeriodicalTypes());
        request.setAttribute(Attributes.FREQUENCIES, periodicalService.findAllFrequencies());
        request.setAttribute(Attributes.PUBLISHERS, periodicalService.findAllPublishers());

        LOGGER.info("Periodical editing failed");
        return CommandResult.forward(Views.EDIT_PERIODICAL_VIEW);
    }
}
