package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.PeriodicalIssue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PeriodicalIssueMapper implements EntityMapper<PeriodicalIssue> {
    private static final String ID_FIELD = "periodical_issues_id";
    private static final String ISSUE_NAME_FIELD = "issues_name";
    private static final String ISSUE_NO_FIELD = "issue_no";
    private static final String PUBLICATION_DATE_FIELD = "publication_date";
    private static final String ISSUE_DESCRIPTION_FIELD = "issues_description";

    private final EntityMapper<Periodical> periodicalMapper;

    public PeriodicalIssueMapper() {
        this(new PeriodicalMapper());
    }

    public PeriodicalIssueMapper(EntityMapper<Periodical> periodicalMapper) {
        this.periodicalMapper = periodicalMapper;
    }

    @Override
    public PeriodicalIssue mapToObject(ResultSet resultSet, String tablePrefix) throws SQLException {
        Periodical tempPeriodical = periodicalMapper.mapToObject(resultSet);

        return PeriodicalIssue.newBuilder()
                .setId(resultSet.getLong(
                        tablePrefix + ID_FIELD))
                .setPeriodical(tempPeriodical)
                .setName(resultSet.getString(
                        tablePrefix + ISSUE_NAME_FIELD))
                .setIssueNumber(resultSet.getString(
                        tablePrefix + ISSUE_NO_FIELD))
                .setPublicationDate(resultSet.getObject(
                        tablePrefix + PUBLICATION_DATE_FIELD, LocalDate.class))
                .setDescription(resultSet.getString(
                        tablePrefix + ISSUE_DESCRIPTION_FIELD))
                .build();
    }
}
