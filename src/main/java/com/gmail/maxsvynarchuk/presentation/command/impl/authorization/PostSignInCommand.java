package com.gmail.maxsvynarchuk.presentation.command.impl.authorization;

import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.constants.*;
import com.gmail.maxsvynarchuk.presentation.util.validator.ValidatorManager;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;


public class PostSignInCommand implements Command {
    private static Logger LOGGER = LoggerFactory.getLogger(PostSignInCommand.class);
    private final UserService userService = ServiceFactory.getUserService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Start of sign in process");
        User userDTO = User.newBuilder()
                .setEmail(request.getParameter(RequestParameters.EMAIL))
                .setPassword(request.getParameter(RequestParameters.PASSWORD))
                .build();

        Map<String, Boolean> errors = ValidatorManager
                .validateSignInParameters(userDTO);

        if (errors.isEmpty()) {
            LOGGER.info("Try to sign in");
            Optional<User> userOpt = userService.signIn(userDTO.getEmail(), userDTO.getPassword());
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                user.setPassword(null);
                request.getSession().setAttribute(Attributes.USER, user);
                LOGGER.info("User successfully signed in");
                //TODO
                if (user.isAdmin()) {
                    return CommandResult.redirect(PagesPaths.HOME_PATH);
                } else {
                    return CommandResult.redirect(PagesPaths.CATALOG_PATH);
                }
            } else {
                LOGGER.info("Email and password don't matches");
                errors.put(Attributes.ERROR_AUTHENTICATION, true);
            }
        } else {
            LOGGER.info("Invalid authentication parameters");
        }
        request.setAttribute(Attributes.ERRORS, errors);
        request.setAttribute(Attributes.USER, userDTO);
        LOGGER.info("User fail sign in");
        return CommandResult.forward(Views.SIGN_IN_VIEW);
    }
}
