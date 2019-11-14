package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.Frequency;
import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.PeriodicalType;
import com.gmail.maxsvynarchuk.persistence.entity.Publisher;
import com.gmail.maxsvynarchuk.util.PeriodicalStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PeriodicalMapper implements EntityMapper<Periodical> {
    private static final String ID_FIELD = "periodical_id";
    private static final String PERIODICAL_NAME_FIELD = "periodical_name";
    private static final String PERIODICAL_STATUS_FIELD = "periodical_status";
    private static final String PERIODICAL_PRICE_FIELD = "periodical_price";
    private static final String PERIODICAL_DESCRIPTION_FIELD = "periodical_description";

    private final EntityMapper<Publisher> publisherMapper;
    private final EntityMapper<Frequency> frequencyMapper;
    private final EntityMapper<PeriodicalType> periodicalTypeMapper;

    public PeriodicalMapper() {
        this(new PublisherMapper(),
                new FrequencyMapper(),
                new PeriodicalTypeMapper());
    }

    public PeriodicalMapper(EntityMapper<Publisher> publisherMapper,
                            EntityMapper<Frequency> frequencyMapper,
                            EntityMapper<PeriodicalType> periodicalTypeMapper) {
        this.publisherMapper = publisherMapper;
        this.frequencyMapper = frequencyMapper;
        this.periodicalTypeMapper = periodicalTypeMapper;
    }

    @Override
    public Periodical mapToObject(ResultSet resultSet, String tablePrefix) throws SQLException {
        Publisher tempPublisher = publisherMapper.mapToObject(resultSet);
        Frequency tempFrequency = frequencyMapper.mapToObject(resultSet);
        PeriodicalType tempPeriodicalType = periodicalTypeMapper.mapToObject(resultSet);

        return Periodical.newBuilder()
                .setId(resultSet.getLong(
                        tablePrefix + ID_FIELD))
                .setPublisher(tempPublisher)
                .setFrequency(tempFrequency)
                .setPeriodicalType(tempPeriodicalType)
                .setName(resultSet.getString(
                        tablePrefix + PERIODICAL_NAME_FIELD))
                .setStatus(PeriodicalStatus.valueOf(
                        resultSet.getString(tablePrefix + PERIODICAL_STATUS_FIELD).toUpperCase()))
                .setPrice(resultSet.getBigDecimal(
                        tablePrefix + PERIODICAL_PRICE_FIELD))
                .setDescription(resultSet.getString(
                        tablePrefix + PERIODICAL_DESCRIPTION_FIELD))
                .build();
    }
}
