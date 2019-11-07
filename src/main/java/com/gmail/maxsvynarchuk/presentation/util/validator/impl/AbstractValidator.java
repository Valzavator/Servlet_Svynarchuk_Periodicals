package com.gmail.maxsvynarchuk.presentation.util.validator.impl;

import com.gmail.maxsvynarchuk.presentation.util.validator.Validator;

public abstract class AbstractValidator<T> implements Validator<T> {
    /**
     * Indicates if error occurs in validation process
     */
    private boolean isError;

    @Override
    public void resetErrorStatus() {
        isError = false;
    }

    void setErrorStatus(boolean status) {
        isError = status;
    }
}
