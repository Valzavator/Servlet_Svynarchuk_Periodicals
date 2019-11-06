package com.gmail.maxsvynarchuk.presentation.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The filter restricts access for non-authorized users.
 *
 * @author Maksym Svynarhchuk
 */
public class AuthorizationFilter implements Filter {
    private static final Set<String> secureCommands = new HashSet<>();

    static {
//        secureCommands.add(FIND_USER_SENDING_NOTIFICATION_COMMAND);
//        secureCommands.add(FIND_USER_SETTING_GRADE_COMMAND);
//        secureCommands.add(SET_GRADE_COMMAND);
//        secureCommands.add(SET_USER_STATUS_COMMAND);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//        String securePath = req.getContextPath() + "/admin";
//        String command = req.getParameter(RequestParameters.COMMAND);
//        boolean isSecurePageRequest = req.getRequestURI().startsWith(securePath);
//        boolean isSecureCommandRequest = isSecureCommand(command);
//
//        if (isSecurePageRequest || isSecureCommandRequest) {
//            if (isUserAdmin(req)) {
//                chain.doFilter(req, resp);
//            } else {
//                RequestDispatcher dispatcher = req.getRequestDispatcher(Path.ACCESS_DENIED_PAGE);
//                dispatcher.forward(req, response);
//            }
//        } else {
//            chain.doFilter(req, resp);
//        }
    }

//    private boolean isSecureCommand(String command) {
//        if (Objects.isNull(command))
//            return false;
//        return secureCommands.contains(command);
//    }
//
//    private boolean isUserAdmin(HttpServletRequest req) {
//        HttpSession session = req.getSession(false);
//        boolean isLoggedIn = Objects.nonNull(session) && Objects.nonNull(session.getAttribute(AttributeNames.USER));
//        if (isLoggedIn) {
//            User user = (User) session.getAttribute(AttributeNames.USER);
//            Integer userTypeId = user.getUserTypeId();
//            if (Objects.isNull(userTypeId)) {
//                return false;
//            }
//            return userTypeId == UserType.ADMIN.getId();
//        } else {
//            return false;
//        }
//    }
}
