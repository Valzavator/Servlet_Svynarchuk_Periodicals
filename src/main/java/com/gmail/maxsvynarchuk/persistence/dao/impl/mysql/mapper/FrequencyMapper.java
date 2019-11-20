package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.Frequency;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FrequencyMapper implements EntityMapper<Frequency> {
    private static final String ID_FIELD = "frequency_id";
    private static final String FREQUENCY_NAME_FIELD = "frequency_name";
    private static final String MEANING_FIELD = "meaning";

    @Override
    public Frequency mapToObject(ResultSet resultSet, String tablePrefix)
            throws SQLException {
        return Frequency.newBuilder()
                .setId(resultSet.getInt(
                        tablePrefix + ID_FIELD))
                .setName(resultSet.getString(
                        tablePrefix + FREQUENCY_NAME_FIELD))
                .setMeaning(resultSet.getString(
                        tablePrefix + MEANING_FIELD))
                .build();
    }
}
