package com.gmail.maxsvynarchuk.presentation.util.validator;

import com.gmail.maxsvynarchuk.presentation.command.impl.authorization.PostSignInCommand;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.validator.impl.EmailValidator;
import com.gmail.maxsvynarchuk.presentation.util.validator.impl.PasswordValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manager for validation data in {@link com.gmail.maxsvynarchuk.presentation.command.Command#execute} methods
 *
 * @author Maksym Svynarhcuk
 */
public class ValidatorManager {
    /**
     * Validation data in {@link PostSignInCommand#execute}
     */
    public static Map<String, Boolean> validateSignInParameters(String email, String password) {
        Map<String, Boolean> errors = new HashMap<>();

        validateField(new EmailValidator(), email, Attributes.ERROR_EMAIL, errors);
        validateField(new PasswordValidator(), password, Attributes.ERROR_PASSWORD, errors);

        return errors;
    }

    /**
     * Performs validation of given field with provided validator.
     * If error occurs add {@code ValidationResult} object with error message to list of validation results.
     *
     * @param validator      implementation of {@code Validator}
     * @param field          field for validation
     * @param errorAttribute attribute name for presentation
     * @param errors         container for all {@code ValidationResult}
     * @param <T>            type of field for validation
     * @see Validator
     */
    private static <T> void validateField(Validator<T> validator,
                                          T field,
                                          String errorAttribute,
                                          Map<String, Boolean> errors) {
        if (!validator.isValid(field)) {
            errors.put(errorAttribute, true);
        }
    }
}
