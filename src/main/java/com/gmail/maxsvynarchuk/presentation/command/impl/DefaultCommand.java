package com.gmail.maxsvynarchuk.presentation.command.impl;

import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultCommand implements Command {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(DefaultCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.warn("Wrong request path");
        return CommandResult.redirect(PagesPaths.HOME_PATH);
    }
}
