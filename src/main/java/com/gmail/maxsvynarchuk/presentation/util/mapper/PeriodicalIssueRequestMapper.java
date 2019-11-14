package com.gmail.maxsvynarchuk.presentation.util.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.PeriodicalIssue;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;

public class PeriodicalIssueRequestMapper implements RequestEntityMapper<PeriodicalIssue> {
    @Override
    public PeriodicalIssue mapToObject(HttpServletRequest request) {
        throw new NotImplementedException();
//                return PeriodicalIssue.newBuilder()
//                .setId(resultSet.getLong(
//                        tablePrefix + ID_FIELD))
//                .setPeriodical(tempPeriodical)
//                .setName(resultSet.getString(
//                        tablePrefix + ISSUE_NAME_FIELD))
//                .setIssueNumber(resultSet.getLong(
//                        tablePrefix + ISSUE_NO_FIELD))
//                .setPublicationDate(resultSet.getDate(
//                        tablePrefix + PUBLICATION_DATE_FIELD))
//                .setDescription(resultSet.getString(
//                        tablePrefix + ISSUE_DESCRIPTION_FIELD))
//                .build();
    }
}
