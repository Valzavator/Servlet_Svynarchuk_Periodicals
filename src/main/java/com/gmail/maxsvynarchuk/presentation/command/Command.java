package com.gmail.maxsvynarchuk.presentation.command;

import com.gmail.maxsvynarchuk.presentation.FrontController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Specialize interface for command in front controller pattern.
 *
 * @author Masksym Svynarhcuk
 * @see FrontController
 */
public interface Command {
    /**
     * Process request of user.
     *
     * @param request  HttpServletRequest to be processed
     * @param response HttpServletRequest
     * @return object of {@code CommandResult} class which contains path to appropriate jsp page
     * @see CommandResult
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response);
}
