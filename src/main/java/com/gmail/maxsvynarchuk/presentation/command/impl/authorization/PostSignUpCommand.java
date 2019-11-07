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
import com.gmail.maxsvynarchuk.presentation.util.Util;
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
        if (Util.isAlreadyLoggedIn(request.getSession())) {
            return CommandResult.redirect(PagesPaths.HOME_PATH);
        }

        LOGGER.info("Start of new user registration");
        User userDTO = User.newBuilder()
                .setEmail(request.getParameter(RequestParameters.EMAIL))
                .setPassword(request.getParameter(RequestParameters.PASSWORD))
                .setFirstName(request.getParameter(RequestParameters.FIRST_NAME))
                .setLastName(request.getParameter(RequestParameters.LAST_NAME))
                .setGender(
                        Gender.valueOf(request.getParameter(RequestParameters.GENDER).toUpperCase()))
                .setDateOfBirth(
                        TimeConverter.toDate(request.getParameter(RequestParameters.DATE_OF_BIRTH)))
                .build();

        Map<String, Boolean> errors = ValidatorManager
                .validateSignUpParameters(userDTO);

        if(errors.isEmpty()) {
            boolean isRegistered = userService.registerUser(userDTO);
            if (isRegistered) {
                LOGGER.info("User was successfully register");
                return CommandResult.redirect(PagesPaths.LOGOUT_PATH);
            } else {
                LOGGER.info("Such user already registered");
                errors.put(Attributes.ERROR_REGISTRATION, true);
            }
        } else {
            LOGGER.info("Invalid registration parameters");
            request.setAttribute(Attributes.ERRORS, errors);
            request.setAttribute(Attributes.USER, userDTO);
        }
        LOGGER.info("User registration fail");
        return CommandResult.forward(Views.SIGN_UP_VIEW);
    }
}
