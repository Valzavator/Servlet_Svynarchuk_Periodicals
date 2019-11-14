package com.gmail.maxsvynarchuk.presentation.util.validator.impl;

public class PeriodicalDescriptionValidator extends RegexValidator {
    private final static int MAX_LENGTH = 1000;

    /**
     * Regex used to perform validation of data.
     */
    private static final String EMAIL_REGEX = "^[\\p{L}\\p{Digit}\\p{Punct}\\p{javaWhitespace}]+$";

    public PeriodicalDescriptionValidator() {
        super(EMAIL_REGEX, MAX_LENGTH);
    }
}
