package com.gmail.maxsvynarchuk.presentation.command.impl.authorization;

import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return CommandResult.redirect(PagesPaths.SIGN_IN_PATH);
    }
}
