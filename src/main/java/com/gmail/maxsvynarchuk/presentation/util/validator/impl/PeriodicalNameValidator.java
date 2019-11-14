package com.gmail.maxsvynarchuk.presentation.util.validator.impl;

public class PeriodicalNameValidator extends RegexValidator {
    private final static int MAX_LENGTH = 255;

    /**
     * Regex used to perform validation of data.
     */
    private static final String EMAIL_REGEX = "^[\\p{L}\\p{Digit}\\p{javaWhitespace}]+$";

    public PeriodicalNameValidator() {
        super(EMAIL_REGEX, MAX_LENGTH);
    }
}
