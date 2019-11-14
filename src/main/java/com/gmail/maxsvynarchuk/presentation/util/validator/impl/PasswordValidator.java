package com.gmail.maxsvynarchuk.presentation.util.validator.impl;

import java.util.Objects;

public class PasswordValidator extends AbstractValidator<String> {
    private final static int PASSWORD_MIN_LENGTH = 5;
    private final static int PASSWORD_MAX_LENGTH = 255;

    @Override
    public boolean isValid(String password) {
        resetErrorStatus();
        if(Objects.isNull(password) || password.length() < PASSWORD_MIN_LENGTH ||
                password.length() > PASSWORD_MAX_LENGTH) {
            setErrorStatus(true);
            return false;
        }

        return true;
    }
}
