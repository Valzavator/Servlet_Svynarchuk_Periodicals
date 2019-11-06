package com.gmail.maxsvynarchuk.presentation.command.impl.authorization;

import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.Util;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostLogInCommand implements Command {
    private static Logger LOGGER = LoggerFactory.getLogger(Command.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        if(Util.isAlreadyLoggedIn(request.getSession())) {
            return CommandResult.redirect(PagesPaths.HOME_PATH);
        }

        LOGGER.info("Start of login in process");
        String email = request.getParameter(RequestParameters.EMAIL);
        String password = request.getParameter(RequestParameters.PASSWORD);

        LOGGER.debug(email);
        LOGGER.debug(password);
//
//        try {
//            User user = ServiceFactory.getAuthenticationService().signIn(login, password);
//            req.getSession().setAttribute(AttributeNames.USER, user);
//            LOGGER.info("User successfully signed in");
//            if (user.getUserTypeId() == UserType.STUDENT.getId())
//                return Path.INDEX_PAGE;
//
//            if (user.getUserTypeId() == UserType.ADMIN.getId())
//                return "redirect:" + Path.ADMIN_PAGE;
//
//            throw new RuntimeException("Wrong UserTypeId");
//        } catch (ServiceException e) {
//            LOGGER.info("User fail sign in", e);
//            req.setAttribute(AttributeNames.SIGN_IN_ERROR, e.getMessage());
//            return Path.SIGN_IN_PAGE;
//        }
        return CommandResult.redirect(PagesPaths.HOME_PATH);
    }
}
