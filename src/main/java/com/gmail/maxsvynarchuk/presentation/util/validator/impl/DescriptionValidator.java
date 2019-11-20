package com.gmail.maxsvynarchuk.presentation.util.validator.impl;

public class DescriptionValidator extends RegexValidator {
    private final static int MAX_LENGTH = 1000;

    /**
     * Regex used to perform validation of data.
     */
    private static final String DESCRIPTION_REGEX =
            "^[\\p{L}\\p{Digit}\\p{Punct}\\p{javaWhitespace}]+$";

    public DescriptionValidator() {
        super(DESCRIPTION_REGEX, MAX_LENGTH);
    }
}
