package com.gmail.maxsvynarchuk.presentation.util.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.util.type.Gender;
import com.gmail.maxsvynarchuk.util.TimeConverter;

import javax.servlet.http.HttpServletRequest;

public class SignUpRequestMapper implements RequestEntityMapper<User> {

    @Override
    public User mapToObject(HttpServletRequest request) {
        return User.newBuilder()
                .setEmail(request.getParameter(RequestParameters.USER_EMAIL))
                .setPassword(request.getParameter(RequestParameters.USER_PASSWORD))
                .setFirstName(request.getParameter(RequestParameters.USER_FIRST_NAME))
                .setLastName(request.getParameter(RequestParameters.USER_LAST_NAME))
                .setGender(
                        Gender.valueOf(request.getParameter(RequestParameters.USER_GENDER).toUpperCase()))
                .setDateOfBirth(
                        TimeConverter.toDate(request.getParameter(RequestParameters.USER_DATE_OF_BIRTH)))
                .build();
    }
}
