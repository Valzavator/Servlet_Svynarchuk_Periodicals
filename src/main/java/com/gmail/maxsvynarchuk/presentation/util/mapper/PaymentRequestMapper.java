package com.gmail.maxsvynarchuk.presentation.util.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.Payment;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;

public class PaymentRequestMapper implements RequestEntityMapper<Payment> {
    @Override
    public Payment mapToObject(HttpServletRequest request) {
        throw new NotImplementedException();
//        return Payment.newBuilder()
//                .setId(resultSet.getLong(
//                        tablePrefix + ID_FIELD))
//                .setUser(tempUser)
//                .setPaymentDate(TimeConverter.toDate(
//                        resultSet.getTimestamp(
//                                tablePrefix + PAYMENT_DATE_FIELD)))
//                .setTotalPrice(resultSet.getBigDecimal(
//                        tablePrefix + TOTAL_PRICE_FIELD))
//                .build();
    }
}
