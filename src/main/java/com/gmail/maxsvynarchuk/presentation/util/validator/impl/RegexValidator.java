package com.gmail.maxsvynarchuk.presentation.util.validator.impl;

import java.util.Objects;
import java.util.regex.Pattern;

public class RegexValidator extends AbstractValidator<String> {
    private int maxLength;
    private Pattern pattern;

    public RegexValidator(String regex, int maxLength) {
        this.maxLength = maxLength;
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean isValid(String str) {
        resetErrorStatus();

        if (!Objects.nonNull(str) || str.length() >= maxLength) {
            setErrorStatus(true);
            return false;
        }

        boolean isMatches = pattern.matcher(str).matches();

        if (!isMatches) {
            setErrorStatus(true);
        }

        return isMatches;
    }
}
