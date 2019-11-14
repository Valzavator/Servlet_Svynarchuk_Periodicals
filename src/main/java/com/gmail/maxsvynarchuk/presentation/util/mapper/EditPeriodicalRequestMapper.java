package com.gmail.maxsvynarchuk.presentation.util.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.*;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.util.Gender;
import com.gmail.maxsvynarchuk.util.TimeConverter;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class EditPeriodicalRequestMapper implements RequestEntityMapper<Periodical> {

    @Override
    public Periodical mapToObject(HttpServletRequest request) {
        Long periodicalId = Long.valueOf(
                request.getParameter(RequestParameters.PERIODICAL_ID));
        Periodical periodicalDTO = RequestMapperFactory.getCreatePeriodicalMapper()
                .mapToObject(request);
        periodicalDTO.setId(periodicalId);
        return periodicalDTO;
    }
}
