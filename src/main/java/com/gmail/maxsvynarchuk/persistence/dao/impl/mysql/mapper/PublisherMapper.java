package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PublisherMapper implements EntityMapper<Publisher> {
    private static final String ID_FIELD = "publisher_id";
    private static final String PUBLISHER_NAME_FIELD = "publisher_name";

    @Override
    public Publisher mapToObject(ResultSet resultSet, String tablePrefix)
            throws SQLException {
        long publisherId = resultSet.getLong(tablePrefix + ID_FIELD);
        String publisherName = resultSet.getString(tablePrefix + PUBLISHER_NAME_FIELD);
        return new Publisher(publisherId, publisherName);
    }
}
