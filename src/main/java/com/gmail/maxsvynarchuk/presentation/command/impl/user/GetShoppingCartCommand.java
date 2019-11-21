package com.gmail.maxsvynarchuk.presentation.command.impl.user;

import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.Util;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.service.ServiceFactory;
import com.gmail.maxsvynarchuk.service.ShoppingCartService;
import com.gmail.maxsvynarchuk.service.entity.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetShoppingCartCommand implements Command {
    ShoppingCartService shoppingCartService = ServiceFactory.getShoppingCartService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        ShoppingCart shoppingCart = Util.getShoppingCart(request.getSession());
        shoppingCartService.updateShoppingCartItemsFromDatabase(shoppingCart);
        return CommandResult.forward(Views.CART_VIEW);
    }
}
