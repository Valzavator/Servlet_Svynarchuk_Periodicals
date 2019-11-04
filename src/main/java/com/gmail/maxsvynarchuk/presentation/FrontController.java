package com.gmail.maxsvynarchuk.presentation;

import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandFactory;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.i18n.SupportedLocale;
import com.gmail.maxsvynarchuk.presentation.util.RedirectType;
import com.gmail.maxsvynarchuk.presentation.util.RequestMethod;
import com.gmail.maxsvynarchuk.presentation.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Provide a centralized request handling mechanism to
 * handle all types of requests coming to the application.
 *
 * @author Maksym Svynarchuk
 */
public class FrontController extends HttpServlet {
    private static final String SUPPORTED_LOCALES = "supportedLocales";
    private static final Logger LOGGER = LoggerFactory.getLogger(FrontController.class);

    private CommandFactory commandFactory;

    @Override
    public void init() throws ServletException {
        super.init();
        commandFactory = CommandFactory.getInstance();

//        // TODO
//        Thread.setDefaultUncaughtExceptionHandler(
//                (Thread t, Throwable e) -> LOGGER.error("\n\n\n<<<FrontController>>>", e));

        getServletContext().setAttribute(SUPPORTED_LOCALES,
                SupportedLocale.getSupportedLanguages());
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
//        LOGGER.debug("GET: {}", request.getRequestURI());
        processRequest(request, response, RequestMethod.GET);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
//        LOGGER.debug("POST: {}", request.getRequestURI());
        processRequest(request, response, RequestMethod.POST);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response,
                                RequestMethod method)
            throws ServletException, IOException {
//        System.out.println(">>>>>>>>>>");
//        System.out.println(request.getServletPath());
//        System.out.println(request.getContextPath());
//        System.out.println(request.getRequestURL());
//        System.out.println(request.getRequestURI());
//
//        System.out.println("PATH: " + getPath(request));

        Command command = commandFactory.getCommand(
                getPath(request), method);

        CommandResult commandResult = command.execute(request, response);
//        LOGGER.debug("Path of response: {}", commandResult.getPagePath());
//        if (commandResult.getRedirectType() == RedirectType.REDIRECT) {
//            Util.redirectTo(request, response, commandResult.getPagePath());
//        } else {
//            request.getRequestDispatcher(commandResult.getPagePath())
//                    .forward(request, response);
//        }
    }

    private String getPath(HttpServletRequest request) {
        return request.getRequestURI()
                .replaceFirst(request.getContextPath(), "")
                .replaceFirst(request.getServletPath(), "");
    }

}
