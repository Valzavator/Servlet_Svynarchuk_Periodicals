package com.gmail.maxsvynarchuk.presentation.util.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.*;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class CreateIssueRequestMapper implements RequestEntityMapper<PeriodicalIssue> {

    @Override
    public PeriodicalIssue mapToObject(HttpServletRequest request) {
        Long periodicalId = Long.valueOf(
                request.getParameter(RequestParameters.PERIODICAL_ID));

        Periodical periodical = Periodical.newBuilder()
                .setId(periodicalId)
                .build();

        return PeriodicalIssue.newBuilder()
                .setName(request.getParameter(RequestParameters.ISSUE_NAME))
                .setIssueNumber(request.getParameter(RequestParameters.ISSUE_NUMBER))
                .setDescription(request.getParameter(RequestParameters.ISSUE_DESCRIPTION))
                .setPublicationDate(
                        LocalDate.parse(request.getParameter(RequestParameters.ISSUE_PUBLICATION_DATE)))
                .setPeriodical(periodical)
                .build();
    }
}
