package com.gmail.maxsvynarchuk.presentation.util.validator.impl;

public class NameValidator extends RegexValidator {
    private final static int MAX_LENGTH = 255;

    /**
     * Regex used to perform validation of name.
     */
    private static final String NAME_REGEX = "^\\p{Lu}[\\p{L}&&[^\\p{Lu}]]+$";

    public NameValidator() {
        super(NAME_REGEX, MAX_LENGTH);
    }
}