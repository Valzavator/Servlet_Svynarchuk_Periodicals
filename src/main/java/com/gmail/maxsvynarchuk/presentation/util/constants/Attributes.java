package com.gmail.maxsvynarchuk.presentation.util.constants;

import com.gmail.maxsvynarchuk.util.ResourceManager;

/**
 * Constants which relates to view part
 */
public final class Attributes {
    public static final String USER = ResourceManager.ATTRIBUTE.getProperty("user");

    public static final String ERRORS = ResourceManager.ATTRIBUTE.getProperty("errors");
    public static final String ERROR_EMAIL = ResourceManager.ATTRIBUTE.getProperty("error.email");
    public static final String ERROR_PASSWORD = ResourceManager.ATTRIBUTE.getProperty("error.password");
    public static final String ERROR_FIRST_NAME = ResourceManager.ATTRIBUTE.getProperty("error.firstname");
    public static final String ERROR_LAST_NAME = ResourceManager.ATTRIBUTE.getProperty("error.lastname");
    public static final String ERROR_AUTHENTICATION = ResourceManager.ATTRIBUTE.getProperty("error.authentication");
    public static final String ERROR_REGISTRATION = ResourceManager.ATTRIBUTE.getProperty("error.registration");
    public static final String ERROR_IS_ALREADY_SUBSCRIBED = ResourceManager.ATTRIBUTE.getProperty("error.is.already.subscribed");

    public static final String PERIODICAL = ResourceManager.ATTRIBUTE.getProperty("periodical");
    public static final String CATALOG = ResourceManager.ATTRIBUTE.getProperty("catalog");
    public static final String SUBSCRIPTION_PLANS = ResourceManager.ATTRIBUTE.getProperty("subscription.plans");

    public static final String PAGINATION_PAGE = ResourceManager.ATTRIBUTE.getProperty("pagination.page");
    public static final String PAGINATION_NUMBER_OF_PAGES = ResourceManager.ATTRIBUTE.getProperty("pagination.number.pages");

    private Attributes() {
    }
}
