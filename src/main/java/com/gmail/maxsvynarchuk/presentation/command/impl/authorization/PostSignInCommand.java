package com.gmail.maxsvynarchuk.presentation.command.impl.authorization;

import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.presentation.util.mapper.RequestMapperFactory;
import com.gmail.maxsvynarchuk.presentation.util.validator.ValidatorManagerFactory;
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
    private UserService userService = ServiceFactory.getUserService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Start of sign in process");
        User userDTO = RequestMapperFactory.getSignInMapper()
                .mapToObject(request);

        Map<String, Boolean> errors = ValidatorManagerFactory.getSignInValidator()
                .validate(userDTO);

        if (errors.isEmpty()) {
            LOGGER.debug("Try to sign in");
            Optional<User> userOpt =
                    userService.signIn(userDTO.getEmail(), userDTO.getPassword());
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                request.getSession().setAttribute(Attributes.USER, user);
                LOGGER.debug("User successfully signed in");
                if (user.isAdmin()) {
                    return CommandResult.redirect(PagesPaths.ADMIN_CATALOG_PATH);
                } else {
                    return CommandResult.redirect(PagesPaths.CATALOG_PATH);
                }
            } else {
                LOGGER.debug("Email and password don't matches");
                errors.put(Attributes.ERROR_AUTHENTICATION, true);
            }
        } else {
            LOGGER.debug("Invalid authentication parameters");
        }
        request.setAttribute(Attributes.ERRORS, errors);
        request.setAttribute(Attributes.USER_DTO, userDTO);
        LOGGER.debug("User fail sign in");
        return CommandResult.forward(Views.SIGN_IN_VIEW);
    }
}
