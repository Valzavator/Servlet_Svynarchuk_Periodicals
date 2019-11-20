package com.gmail.maxsvynarchuk.presentation.command.impl.authorization;

import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.util.mapper.RequestMapperFactory;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.validator.ValidatorManager;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.service.UserService;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class PostSignUpCommand implements Command {
    private static Logger LOGGER = LoggerFactory.getLogger(PostSignUpCommand.class);
    private final UserService userService = ServiceFactory.getUserService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Start of new user registration");
        User userDTO = RequestMapperFactory.getSignUpMapper()
                .mapToObject(request);

        Map<String, Boolean> errors = ValidatorManager
                .validateSignUpParameters(userDTO);

        if (errors.isEmpty()) {
            boolean isRegistered = userService.registerUser(userDTO);
            if (isRegistered) {
                LOGGER.debug("User was successfully register");
                return CommandResult.redirect(PagesPaths.SIGN_IN_PATH);
            } else {
                LOGGER.debug("Such user already registered");
                errors.put(Attributes.ERROR_REGISTRATION, true);
            }
        } else {
            LOGGER.debug("Invalid registration parameters");
        }
        request.setAttribute(Attributes.ERRORS, errors);
        request.setAttribute(Attributes.USER_DTO, userDTO);
        LOGGER.debug("User registration fail");
        return CommandResult.forward(Views.SIGN_UP_VIEW);
    }
}
