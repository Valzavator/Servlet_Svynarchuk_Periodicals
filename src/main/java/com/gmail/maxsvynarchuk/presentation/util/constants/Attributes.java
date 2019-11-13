package com.gmail.maxsvynarchuk.presentation.util.constants;

import com.gmail.maxsvynarchuk.util.ResourceManager;

/**
 * Constants which relates to view part
 */
public final class Attributes {
    public static final String USER = ResourceManager.ATTRIBUTE.getProperty("user");
    public static final String PERIODICAL = ResourceManager.ATTRIBUTE.getProperty("periodical");

    public static final String USER_DTO = ResourceManager.ATTRIBUTE.getProperty("user.dto");
    public static final String PERIODICAL_DTO = ResourceManager.ATTRIBUTE.getProperty("periodical.dto");

    public static final String ERRORS = ResourceManager.ATTRIBUTE.getProperty("errors");
    public static final String ERROR_EMAIL = ResourceManager.ATTRIBUTE.getProperty("error.email");
    public static final String ERROR_PASSWORD = ResourceManager.ATTRIBUTE.getProperty("error.password");
    public static final String ERROR_FIRST_NAME = ResourceManager.ATTRIBUTE.getProperty("error.firstname");
    public static final String ERROR_LAST_NAME = ResourceManager.ATTRIBUTE.getProperty("error.lastname");
    public static final String ERROR_AUTHENTICATION = ResourceManager.ATTRIBUTE.getProperty("error.authentication");
    public static final String ERROR_REGISTRATION = ResourceManager.ATTRIBUTE.getProperty("error.registration");
    public static final String ERROR_IS_ALREADY_SUBSCRIBED = ResourceManager.ATTRIBUTE.getProperty("error.is.already.subscribed");
    public static final String ERROR_PERIODICAL_NAME = ResourceManager.ATTRIBUTE.getProperty("error.periodical.name");
    public static final String ERROR_PERIODICAL_DESCRIPTION = ResourceManager.ATTRIBUTE.getProperty("error.periodical.description");
    public static final String ERROR_PERIODICAL_PRICE = ResourceManager.ATTRIBUTE.getProperty("error.periodical.price");

    public static final String CATALOG = ResourceManager.ATTRIBUTE.getProperty("catalog");
    public static final String SUBSCRIPTION_PLANS = ResourceManager.ATTRIBUTE.getProperty("subscription.plans");
    public static final String PERIODICAL_TYPES = ResourceManager.ATTRIBUTE.getProperty("periodical.types");
    public static final String FREQUENCIES = ResourceManager.ATTRIBUTE.getProperty("frequencies");
    public static final String PUBLISHERS = ResourceManager.ATTRIBUTE.getProperty("publishers");

    public static final String PAGINATION_PAGE = ResourceManager.ATTRIBUTE.getProperty("pagination.page");
    public static final String PAGINATION_NUMBER_OF_PAGES = ResourceManager.ATTRIBUTE.getProperty("pagination.number.pages");

    private Attributes() {
    }
}
