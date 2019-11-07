package com.gmail.maxsvynarchuk.presentation.command.impl.authorization;

import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.Util;
import com.gmail.maxsvynarchuk.presentation.util.constants.*;
import com.gmail.maxsvynarchuk.presentation.util.validator.ValidatorManager;
import com.gmail.maxsvynarchuk.service.AuthenticationService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class PostSignInCommand implements Command {
    private static Logger LOGGER = LoggerFactory.getLogger(Command.class);

    private final AuthenticationService authenticationService = ServiceFactory.getAuthenticationService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        if (Util.isAlreadyLoggedIn(request.getSession())) {
            return CommandResult.redirect(PagesPaths.HOME_PATH);
        }

        LOGGER.info("Start of sign in process");
        User userDTO = User.newBuilder()
                .setEmail(request.getParameter(RequestParameters.EMAIL))
                .setPassword(request.getParameter(RequestParameters.PASSWORD))
                .build();

        Map<String, Boolean> errors = ValidatorManager
                .validateSignInParameters(userDTO.getEmail(), userDTO.getPassword());

        if (errors.isEmpty()) {
            Optional<User> userOpt = authenticationService.signIn(userDTO.getEmail(), userDTO.getPassword());
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                user.setPassword(null);
                request.getSession().setAttribute(Attributes.USER, user);

                LOGGER.info("User successfully signed in");
//                //TODO
//                if (user.getRole().getId() == UserType.USER.getId())
//                    return CommandResult.redirect(PagesPaths.HOME_PATH);
//                if (user.getRole().getId() == UserType.ADMIN.getId())
//                    return CommandResult.redirect(PagesPaths.HOME_PATH);
                return CommandResult.redirect(PagesPaths.HOME_PATH);
            } else {
                LOGGER.info("Email and password don't matches");
                request.setAttribute(Attributes.ERROR_AUTHENTICATION, true);
            }
        } else {
            LOGGER.info("Invalid parameters");
            request.setAttribute(Attributes.ERRORS, errors);
            request.setAttribute(Attributes.USER, userDTO);
        }

        LOGGER.info("User fail sign in");
        return CommandResult.forward(Views.SIGN_IN_VIEW);
    }
}
