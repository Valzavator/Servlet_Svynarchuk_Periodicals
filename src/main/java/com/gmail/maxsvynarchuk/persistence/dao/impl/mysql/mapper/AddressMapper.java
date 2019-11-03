package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.Address;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressMapper implements EntityMapper<Address> {
    private static final String ID_FIELD = "address_id";
    private static final String COUNTRY_FIELD = "country";
    private static final String CITY_FIELD = "city";
    private static final String POST_INDEX_FIELD = "post_index";
    private static final String DETAIL_ADDRESS_FIELD = "detail_address";

    @Override
    public Address mapToObject(ResultSet resultSet, String tablePrefix) throws SQLException {
        return Address.newBuilder()
                .setId(resultSet.getLong(
                        tablePrefix + ID_FIELD))
                .setCountry(resultSet.getString(
                        tablePrefix + COUNTRY_FIELD))
                .setCity(resultSet.getString(
                        tablePrefix + CITY_FIELD))
                .setPostIndex(resultSet.getString(
                        tablePrefix + POST_INDEX_FIELD))
                .setDetailAddress(resultSet.getString(
                        tablePrefix + DETAIL_ADDRESS_FIELD))
                .build();
    }
}
