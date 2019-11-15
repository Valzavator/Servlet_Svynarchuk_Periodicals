package com.gmail.maxsvynarchuk.presentation.util.validator.impl;

public class TitleValidator extends RegexValidator {
    private final static int MAX_LENGTH = 255;

    /**
     * Regex used to perform validation of data.
     */
    private static final String TITLE_REGEX = "^[\\p{L}\\p{Digit}\\p{javaWhitespace}]+$";

    public TitleValidator() {
        super(TITLE_REGEX, MAX_LENGTH);
    }
}
