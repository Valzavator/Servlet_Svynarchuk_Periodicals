package com.gmail.maxsvynarchuk.presentation.command.impl.admin;

import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.exception.NotFoundException;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class GetUserProfileCommand implements Command {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(GetUserProfileCommand.class);
    private final UserService userService = ServiceFactory.getUserService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Attempt to get a user profile page");
        Long userId = Long.valueOf(
                request.getParameter(RequestParameters.USER_ID));
        Optional<User> userOpt = userService.findUserById(userId);
        if (userOpt.isPresent()) {
            request.setAttribute(Attributes.USER_DTO, userOpt.get());
            LOGGER.debug("Attempt to get a user profile page is successful");
            return CommandResult.forward(Views.USER_VIEW);
        }
        LOGGER.debug("User with id {} doesn't exist", userId);
        throw new NotFoundException();
    }
}
