package com.gmail.maxsvynarchuk.presentation.filter;

import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.util.RoleType;

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
    private static final Set<String> secureAdminPaths = new HashSet<>();
    private static final Set<String> secureUserPaths = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //TODO add all secure paths
//        secureAdminPaths.add(PagesPaths.);

        secureUserPaths.add(PagesPaths.CART_PATH);
        secureUserPaths.add(PagesPaths.CART_ADD_ITEM_PATH);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {

            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession session = req.getSession(false);

            User user = Objects.nonNull(session)
                    ? (User) session.getAttribute(Attributes.USER)
                    : null;

            boolean isUser = RoleType.USER.isEquals(user);
            boolean isAdmin = RoleType.ADMIN.isEquals(user);
            boolean isOnlyAdminRequest = secureAdminPaths.contains(req.getPathInfo());
            boolean isOnlyUserRequest = secureUserPaths.contains(req.getPathInfo());

            if (isOnlyAdminRequest) {
                doFilter(isAdmin, req, resp, chain);
            } else if (isOnlyUserRequest) {
                doFilter(isUser, req, resp, chain);
            } else {
                chain.doFilter(req, resp);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void doFilter(boolean isAuthorized,
                          ServletRequest request,
                          ServletResponse response,
                          FilterChain chain) throws IOException, ServletException {
        if (isAuthorized) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher(Views.ERROR_403_VIEW)
                    .forward(request, response);
        }
    }
}
