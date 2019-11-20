package com.gmail.maxsvynarchuk.presentation.command.impl.admin;

import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class GetUserProfileCommand implements Command {
    UserService userService = ServiceFactory.getUserService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        Long userId = Long.valueOf(
                request.getParameter(RequestParameters.USER_ID));
        Optional<User> userOpt = userService.findById(userId);

        if (userOpt.isPresent()) {
            request.setAttribute(Attributes.USER_DTO, userOpt.get());
            return CommandResult.forward(Views.USER_VIEW);
        } else {
            return CommandResult.forward(Views.ERROR_404_VIEW);
        }
    }
}
