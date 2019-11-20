package com.gmail.maxsvynarchuk.presentation.util.validator.impl;

import com.gmail.maxsvynarchuk.presentation.util.validator.Validator;

import java.util.Objects;

public class PasswordValidator implements Validator<String> {
    private final static int PASSWORD_MIN_LENGTH = 5;
    private final static int PASSWORD_MAX_LENGTH = 255;

    @Override
    public boolean isValid(String password) {
        return Objects.nonNull(password) &&
                password.length() >= PASSWORD_MIN_LENGTH &&
                password.length() <= PASSWORD_MAX_LENGTH;
    }
}
