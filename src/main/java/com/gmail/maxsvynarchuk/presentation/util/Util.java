package com.gmail.maxsvynarchuk.presentation.util;

import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Util {
    /**
     * Add next page to redirect
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param pageToRedirect page to redirect
     * @throws IOException IOException
     */
    public static void redirectTo(HttpServletRequest request,
                                  HttpServletResponse response,
                                  String pageToRedirect) throws IOException {
        response.sendRedirect(
                request.getContextPath() + request.getServletPath() + pageToRedirect);
    }

    /**
     * Check if the user is logged in
     *
     * @param session HttpSession
     * @return {@code true} if logged in else {@code false}
     */
    public static boolean isAlreadyLoggedIn(HttpSession session) {
        return getAuthorizedUser(session) != null;
    }

    /**
     * Get authorized user
     *
     * @param session HttpSession
     * @return authorized user else {@code null}
     */
    public static User getAuthorizedUser(HttpSession session) {
        return (User) session.getAttribute(Attributes.USER);
    }
}
