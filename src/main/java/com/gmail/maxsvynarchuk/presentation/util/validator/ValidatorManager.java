package com.gmail.maxsvynarchuk.presentation.util.validator;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.command.impl.admin.PostCreatePeriodicalCommand;
import com.gmail.maxsvynarchuk.presentation.command.impl.authorization.PostSignInCommand;
import com.gmail.maxsvynarchuk.presentation.command.impl.authorization.PostSignUpCommand;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.validator.impl.*;

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
    public static Map<String, Boolean> validateSignInParameters(User userDTO) {
        Map<String, Boolean> errors = new HashMap<>();

        validateField(new EmailValidator(),
                userDTO.getEmail(),
                Attributes.ERROR_EMAIL,
                errors);
        validateField(new PasswordValidator(),
                userDTO.getPassword(),
                Attributes.ERROR_PASSWORD,
                errors);

        return errors;
    }

    /**
     * Validation data in {@link PostSignUpCommand#execute}
     */
    public static Map<String, Boolean> validateSignUpParameters(User userDTO) {
        Map<String, Boolean> errors = new HashMap<>();

        validateField(new EmailValidator(),
                userDTO.getEmail(),
                Attributes.ERROR_EMAIL,
                errors);
        validateField(new PasswordValidator(),
                userDTO.getPassword(),
                Attributes.ERROR_PASSWORD,
                errors);
        validateField(new NameValidator(),
                userDTO.getFirstName(),
                Attributes.ERROR_FIRST_NAME,
                errors);
        validateField(new NameValidator(),
                userDTO.getLastName(),
                Attributes.ERROR_LAST_NAME,
                errors);

        return errors;
    }

    /**
     * Validation data in {@link PostCreatePeriodicalCommand#execute}
     */
    public static Map<String, Boolean> validatePeriodicalParameters(Periodical periodicalDTO) {
        Map<String, Boolean> errors = new HashMap<>();

        validateField(new PeriodicalNameValidator(),
                periodicalDTO.getName(),
                Attributes.ERROR_PERIODICAL_NAME,
                errors);
        validateField(new PeriodicalDescriptionValidator(),
                periodicalDTO.getDescription(),
                Attributes.ERROR_PERIODICAL_DESCRIPTION,
                errors);
        validateField(new PeriodicalPriceValidator(),
                periodicalDTO.getPrice(),
                Attributes.ERROR_PERIODICAL_PRICE,
                errors);

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
