package com.gmail.maxsvynarchuk.presentation.util.validator.impl;

import com.gmail.maxsvynarchuk.presentation.util.validator.Validator;

import java.util.Objects;
import java.util.regex.Pattern;

public class RegexValidator implements Validator<String> {
    private int maxLength;
    private Pattern pattern;

    public RegexValidator(String regex, int maxLength) {
        this.maxLength = maxLength;
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean isValid(String str) {
        return Objects.nonNull(str)
                && str.length() <= maxLength
                && pattern.matcher(str).matches();
    }
}
