package com.gmail.maxsvynarchuk.presentation.command.impl.authorization;

import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.util.Gender;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.validator.ValidatorManager;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.service.UserService;
import com.gmail.maxsvynarchuk.util.TimeConverter;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class PostSignUpCommand implements Command {
    private static Logger LOGGER = LoggerFactory.getLogger(PostSignInCommand.class);
    private final UserService userService = ServiceFactory.getUserService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Start of new user registration");
        User userDTO = User.newBuilder()
                .setEmail(request.getParameter(RequestParameters.USER_EMAIL))
                .setPassword(request.getParameter(RequestParameters.USER_PASSWORD))
                .setFirstName(request.getParameter(RequestParameters.USER_FIRST_NAME))
                .setLastName(request.getParameter(RequestParameters.USER_LAST_NAME))
                .setGender(
                        Gender.valueOf(request.getParameter(RequestParameters.USER_GENDER).toUpperCase()))
                .setDateOfBirth(
                        TimeConverter.toDate(request.getParameter(RequestParameters.USER_DATE_OF_BIRTH)))
                .build();

        Map<String, Boolean> errors = ValidatorManager
                .validateSignUpParameters(userDTO);

        if(errors.isEmpty()) {
            boolean isRegistered = userService.registerUser(userDTO);
            if (isRegistered) {
                LOGGER.info("User was successfully register");
                return CommandResult.redirect(PagesPaths.SIGN_IN_PATH);
            } else {
                LOGGER.info("Such user already registered");
                errors.put(Attributes.ERROR_REGISTRATION, true);
            }
        } else {
            LOGGER.info("Invalid registration parameters");
        }
        request.setAttribute(Attributes.ERRORS, errors);
        request.setAttribute(Attributes.USER_DTO, userDTO);
        LOGGER.info("User registration fail");
        return CommandResult.forward(Views.SIGN_UP_VIEW);
    }
}
