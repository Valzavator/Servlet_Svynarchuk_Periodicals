package com.gmail.maxsvynarchuk.presentation.util.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.Frequency;
import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.PeriodicalType;
import com.gmail.maxsvynarchuk.persistence.entity.Publisher;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class CreatePeriodicalRequestMapper implements RequestEntityMapper<Periodical> {

    @Override
    public Periodical mapToObject(HttpServletRequest request) {
        Integer periodicalTypeId = Integer.valueOf(
                request.getParameter(RequestParameters.PERIODICAL_TYPE_ID));
        Integer periodicalFrequencyId = Integer.valueOf(
                request.getParameter(RequestParameters.PERIODICAL_FREQUENCY_ID));
        Long periodicalPublisherId = Long.valueOf(
                request.getParameter(RequestParameters.PERIODICAL_PUBLISHER_ID));

        BigDecimal price = new BigDecimal(
                request.getParameter(RequestParameters.PERIODICAL_PRICE));
        PeriodicalType periodicalType = PeriodicalType.newBuilder()
                .setId(periodicalTypeId)
                .build();
        Frequency frequency = Frequency.newBuilder()
                .setId(periodicalFrequencyId)
                .build();
        Publisher publisher = new Publisher(periodicalPublisherId, null);

        return Periodical.newBuilder()
                .setName(request.getParameter(RequestParameters.PERIODICAL_NAME))
                .setStatus(PeriodicalStatus.ACTIVE)
                .setDescription(request.getParameter(RequestParameters.PERIODICAL_DESCRIPTION))
                .setPrice(price)
                .setPeriodicalType(periodicalType)
                .setFrequency(frequency)
                .setPublisher(publisher)
                .build();
    }
}
