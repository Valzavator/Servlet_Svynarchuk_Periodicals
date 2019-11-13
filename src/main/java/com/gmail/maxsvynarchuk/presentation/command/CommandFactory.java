package com.gmail.maxsvynarchuk.presentation.command;

import com.gmail.maxsvynarchuk.presentation.command.impl.*;
import com.gmail.maxsvynarchuk.presentation.command.impl.admin.GetAdminCatalogCommand;
import com.gmail.maxsvynarchuk.presentation.command.impl.admin.GetCreatePeriodicalCommand;
import com.gmail.maxsvynarchuk.presentation.command.impl.admin.PostCreatePeriodicalCommand;
import com.gmail.maxsvynarchuk.presentation.command.impl.authorization.*;
import com.gmail.maxsvynarchuk.presentation.command.impl.common.GetCatalogCommand;
import com.gmail.maxsvynarchuk.presentation.command.impl.common.GetPeriodicalCommand;
import com.gmail.maxsvynarchuk.presentation.command.impl.user.GetShoppingCartCommand;
import com.gmail.maxsvynarchuk.presentation.command.impl.user.PostCartAddItemCommand;
import com.gmail.maxsvynarchuk.presentation.util.RequestMethod;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final static String DELIMITER = ":";

    private final Command DEFAULT_COMMAND = new DefaultCommand();
    private final Map<String, Command> commands = new HashMap<>();

    private CommandFactory() {
        init();
    }

    private void init() {
        commands.put(buildKey(PagesPaths.HOME_PATH, RequestMethod.GET),
                new HomeCommand());
        commands.put(buildKey(PagesPaths.SIGN_IN_PATH, RequestMethod.GET),
                new GetSignInCommand());
        commands.put(buildKey(PagesPaths.SIGN_IN_PATH, RequestMethod.POST),
                new PostSignInCommand());
        commands.put(buildKey(PagesPaths.SIGN_UP_PATH, RequestMethod.GET),
                new GetSignUpCommand());
        commands.put(buildKey(PagesPaths.SIGN_UP_PATH, RequestMethod.POST),
                new PostSignUpCommand());
        commands.put(buildKey(PagesPaths.SIGN_OUT_PATH, RequestMethod.GET),
                new SignOutCommand());
        commands.put(buildKey(PagesPaths.PERIODICAL_PATH, RequestMethod.GET),
                new GetPeriodicalCommand());
        commands.put(buildKey(PagesPaths.CATALOG_PATH, RequestMethod.GET),
                new GetCatalogCommand());
        commands.put(buildKey(PagesPaths.CART_PATH, RequestMethod.GET),
                new GetShoppingCartCommand());
        commands.put(buildKey(PagesPaths.CART_ADD_ITEM_PATH, RequestMethod.POST),
                new PostCartAddItemCommand());
        commands.put(buildKey(PagesPaths.ADMIN_CATALOG_PATH, RequestMethod.GET),
                new GetAdminCatalogCommand());
        commands.put(buildKey(PagesPaths.CREATE_PERIODICAL_PATH, RequestMethod.GET),
                new GetCreatePeriodicalCommand());
        commands.put(buildKey(PagesPaths.CREATE_PERIODICAL_PATH, RequestMethod.POST),
                new PostCreatePeriodicalCommand());
    }

    public Command getCommand(String path, RequestMethod method) {
        return commands.getOrDefault(buildKey(path, method), DEFAULT_COMMAND);
    }

    private String buildKey(String path, RequestMethod method) {
        return method.name() + DELIMITER + path;
    }

    public static class Singleton {
        private final static CommandFactory INSTANCE = new CommandFactory();
    }

    public static CommandFactory getInstance() {
        return Singleton.INSTANCE;
    }
}
