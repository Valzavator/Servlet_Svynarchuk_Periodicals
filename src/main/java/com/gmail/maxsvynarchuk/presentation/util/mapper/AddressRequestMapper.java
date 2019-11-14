package com.gmail.maxsvynarchuk.presentation.util.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.Address;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;

public class AddressRequestMapper implements RequestEntityMapper<Address> {

    @Override
    public Address mapToObject(HttpServletRequest request) {
        throw new NotImplementedException();
//        return Address.newBuilder()
//                .setId(resultSet.getLong(
//                        tablePrefix + ID_FIELD))
//                .setCountry(resultSet.getString(
//                        tablePrefix + COUNTRY_FIELD))
//                .setCity(resultSet.getString(
//                        tablePrefix + CITY_FIELD))
//                .setPostIndex(resultSet.getString(
//                        tablePrefix + POST_INDEX_FIELD))
//                .setDetailAddress(resultSet.getString(
//                        tablePrefix + DETAIL_ADDRESS_FIELD))
//                .build();
    }
}
