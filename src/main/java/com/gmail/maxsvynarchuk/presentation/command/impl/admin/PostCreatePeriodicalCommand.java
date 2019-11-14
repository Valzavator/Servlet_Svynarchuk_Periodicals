package com.gmail.maxsvynarchuk.presentation.command.impl.admin;

import com.gmail.maxsvynarchuk.persistence.entity.*;
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

public class PostCreatePeriodicalCommand implements Command {
    private static Logger LOGGER = LoggerFactory.getLogger(PostSignInCommand.class);
    private final PeriodicalService periodicalService = ServiceFactory.getPeriodicalService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Start of new periodical creation");

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
            periodicalService.createPeriodical(periodicalDTO);
            LOGGER.info("Periodical was successfully create");
            return CommandResult.redirect(PagesPaths.CATALOG_PATH);
        }

        LOGGER.info("Invalid creation parameters");
        request.setAttribute(Attributes.ERRORS, errors);
        request.setAttribute(Attributes.PERIODICAL_DTO, periodicalDTO);
        request.setAttribute(Attributes.PERIODICAL_TYPES, periodicalService.findAllPeriodicalTypes());
        request.setAttribute(Attributes.FREQUENCIES, periodicalService.findAllFrequencies());
        request.setAttribute(Attributes.PUBLISHERS, periodicalService.findAllPublishers());

        LOGGER.info("Periodical creation fail");
        return CommandResult.forward(Views.CREATE_PERIODICAL_VIEW);
    }
}
