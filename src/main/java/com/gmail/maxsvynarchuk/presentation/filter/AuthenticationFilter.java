package com.gmail.maxsvynarchuk.presentation.filter;


import com.gmail.maxsvynarchuk.presentation.util.Util;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * The filter restricts access for non-authenticated users.
 *
 * @author Maksym Svynarhchuk
 */
public class AuthenticationFilter implements Filter {
    private static final Set<String> freePaths = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        freePaths.add(PagesPaths.HOME_PATH);
        freePaths.add(PagesPaths.SIGN_IN_PATH);
        freePaths.add(PagesPaths.SIGN_UP_PATH);
        freePaths.add(PagesPaths.PERIODICAL_PATH);
        freePaths.add(PagesPaths.CATALOG_PATH);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String requestPath = req.getPathInfo();

        boolean isLoggedIn = Objects.nonNull(session) &&
                Objects.nonNull(session.getAttribute(Attributes.USER));
        boolean isSignUpRequest = PagesPaths.SIGN_UP_PATH.equals(requestPath);
        boolean isSignInRequest = PagesPaths.SIGN_IN_PATH.equals(requestPath);

        if (isLoggedIn) {
            if (isSignUpRequest || isSignInRequest) {
                Util.redirectTo(req, resp, PagesPaths.HOME_PATH);
            } else {
                chain.doFilter(req, resp);
            }
        } else {
            if (freePaths.contains(requestPath)) {
                chain.doFilter(req, resp);
            } else {
                Util.redirectTo(req, resp, PagesPaths.SIGN_IN_PATH);
            }
        }
    }

}
