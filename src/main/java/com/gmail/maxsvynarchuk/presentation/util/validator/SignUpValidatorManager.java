package com.gmail.maxsvynarchuk.presentation.util.validator;

import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.command.impl.authorization.PostSignUpCommand;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.validator.impl.EmailValidator;
import com.gmail.maxsvynarchuk.presentation.util.validator.impl.PasswordValidator;
import com.gmail.maxsvynarchuk.presentation.util.validator.impl.UserNameValidator;

import java.io.Serializable;
import java.util.Map;

/**
 * Validation data in {@link PostSignUpCommand#execute}
 *
 * @author Maksym Svynarhcuk
 */
public class SignUpValidatorManager extends ValidatorManager {

    @Override
    protected void validateObject(Map<String, Boolean> errors, Serializable object) {
        if (!(object instanceof User)) {
            throw new IllegalArgumentException("The object is not a type of User");
        }
        User user = (User) object;
        validateField(new EmailValidator(),
                user.getEmail(),
                Attributes.ERROR_EMAIL,
                errors);
        validateField(new PasswordValidator(),
                user.getPassword(),
                Attributes.ERROR_PASSWORD,
                errors);
        validateField(new UserNameValidator(),
                user.getFirstName(),
                Attributes.ERROR_FIRST_NAME,
                errors);
        validateField(new UserNameValidator(),
                user.getLastName(),
                Attributes.ERROR_LAST_NAME,
                errors);
    }
}
