package com.gmail.maxsvynarchuk.presentation.command.impl.user;

import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.Util;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetShoppingCartCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        Util.getShoppingCart(request.getSession());
        return CommandResult.forward(Views.CART_VIEW);
    }
}
