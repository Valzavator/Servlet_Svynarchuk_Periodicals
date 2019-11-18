package com.gmail.maxsvynarchuk.presentation.util.validator.impl;

import java.math.BigDecimal;
import java.util.Objects;

public class PeriodicalPriceValidator extends AbstractValidator<BigDecimal> {
    private final static int PASSWORD_MAX_LENGTH = 255;
    private final static BigDecimal MIN_VALUE = new BigDecimal(0);
    private final static BigDecimal MAX_VALUE = new BigDecimal(100000000L);

    //TODO valid 10 numbers
    @Override
    public boolean isValid(BigDecimal price) {
        resetErrorStatus();
        if(Objects.isNull(price)
                || MIN_VALUE.compareTo(price) > 0
                || MAX_VALUE.compareTo(price) <= 0) {
            setErrorStatus(true);
            return false;
        }

        return true;
    }
}
