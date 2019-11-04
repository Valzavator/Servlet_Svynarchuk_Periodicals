package com.gmail.maxsvynarchuk.presentation.command;

import com.gmail.maxsvynarchuk.presentation.command.impl.DefaultCommand;
import com.gmail.maxsvynarchuk.presentation.util.RequestMethod;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final static String DELIMITER = ":";

    private final Command DEFAULT_COMMAND = new DefaultCommand();
    private Map<String, Command> commands = new HashMap<>();

    private CommandFactory() {
        init();
    }

    private void init() {
//        commands.put(buildKey(PagesPaths.HOME_PATH, Method.GET),
//                new HomeCommand());
//        commands.put(buildKey(PagesPaths.LOGIN_PATH, Method.GET),
//                new GetLoginCommand());
//        commands.put(buildKey(PagesPaths.SIGNUP_PATH, Method.GET),
//                new GetSignupCommand());
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
