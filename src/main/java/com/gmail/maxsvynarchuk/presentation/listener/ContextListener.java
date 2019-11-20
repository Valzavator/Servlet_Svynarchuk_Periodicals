package com.gmail.maxsvynarchuk.presentation.listener;

import com.gmail.maxsvynarchuk.presentation.i18n.SupportedLocale;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Initializes some components before app starts:
 */
public class ContextListener implements ServletContextListener {
    private static final String SUPPORTED_LOCALES = "supportedLocales";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute(SUPPORTED_LOCALES,
                SupportedLocale.getSupportedLanguages());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
