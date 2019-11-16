package com.gmail.maxsvynarchuk.presentation.command.impl.user;

import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.Util;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.service.ShoppingCartService;
import com.gmail.maxsvynarchuk.service.util.entity.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostCartRemoveAllItemCommand implements Command {
    private final ShoppingCartService shoppingCartService = ServiceFactory.getShoppingCartService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        ShoppingCart shoppingCart = Util.getShoppingCart(request.getSession());
        shoppingCartService.removeAllItemFromCart(shoppingCart);
        return CommandResult.redirect(PagesPaths.CART_PATH);
    }
}
