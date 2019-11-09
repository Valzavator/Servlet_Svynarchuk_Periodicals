package com.gmail.maxsvynarchuk.presentation.util;

import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class Util {
    /**
     * Add next page to redirect
     *
     * @param request        HttpServletRequest
     * @param response       HttpServletResponse
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

    /**
     * @return referer path without servlet path at the beginning
     */
    public static String getReferer(HttpServletRequest request) {
        Objects.requireNonNull(request);

        String referer = PagesPaths.HOME_PATH;
        String header = request.getHeader("referer");
        if (header != null && !header.isEmpty()) {
            try {
                URI uri = new URI(header);
                String path = uri.getPath();
                String query = uri.getQuery();
                referer = Objects.isNull(query) ? path : path + "?" + query;
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return referer.replaceFirst(request.getContextPath(), "")
                .replaceFirst(request.getServletPath(), "");
    }

    /**
     * add parameter to exist URI
     */
    public static String addParameterToURI(String uri,
                                           String parameterName,
                                           String parameterValue) {
        Objects.requireNonNull(parameterName);
        Objects.requireNonNull(parameterValue);

        try {
            String newParameter = parameterName + "=" + parameterValue;
            URI oldUri = new URI(uri);
            String newQuery = oldUri.getQuery();
            newQuery = Objects.isNull(newQuery)
                    ? newParameter
                    : newQuery + "&" + newParameter;

            URI newUri = new URI(oldUri.getScheme(), oldUri.getAuthority(),
                    oldUri.getPath(), newQuery, oldUri.getFragment());

            return newUri.getPath() + "?" + newUri.getQuery();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void checkErrorParameter(HttpServletRequest request,
                                          String requestAttribute) {
        String error = request.getParameter(requestAttribute);
        if (Objects.nonNull(error) && !error.isEmpty()) {
            request.setAttribute(error, true);
        }
    }
}
