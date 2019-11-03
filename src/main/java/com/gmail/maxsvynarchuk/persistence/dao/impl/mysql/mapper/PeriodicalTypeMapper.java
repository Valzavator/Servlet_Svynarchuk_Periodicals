package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.PeriodicalType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PeriodicalTypeMapper implements EntityMapper<PeriodicalType> {
    private static final String ID_FIELD = "periodical_type_id";
    private static final String TYPE_NAME_FIELD = "type_name";
    private static final String TYPE_DESCRIPTION_FIELD = "type_description";

    @Override
    public PeriodicalType mapToObject(ResultSet resultSet, String tablePrefix) throws SQLException {
        return PeriodicalType.newBuilder()
                .setId(resultSet.getInt(
                        tablePrefix + ID_FIELD))
                .setName(resultSet.getString(
                        tablePrefix + TYPE_NAME_FIELD))
                .setDescription(resultSet.getString(
                        tablePrefix + TYPE_DESCRIPTION_FIELD))
                .build();
    }
}
