package com.gmail.maxsvynarchuk.presentation.util.constants;

import com.gmail.maxsvynarchuk.util.ResourceManager;

/**
 * Constants which relates to view part
 * */
public final class Attributes {
    public static final String USER = ResourceManager.ATTRIBUTE.getProperty("user");

    public static final String ERRORS = ResourceManager.ATTRIBUTE.getProperty("errors");
    public static final String ERROR_EMAIL = ResourceManager.ATTRIBUTE.getProperty("error.email");
    public static final String ERROR_PASSWORD = ResourceManager.ATTRIBUTE.getProperty("error.password");
    public static final String ERROR_FIRST_NAME = ResourceManager.ATTRIBUTE.getProperty("error.firstname");
    public static final String ERROR_LAST_NAME = ResourceManager.ATTRIBUTE.getProperty("error.lastname");
    public static final String ERROR_AUTHENTICATION = ResourceManager.ATTRIBUTE.getProperty("error.authentication");
    public static final String ERROR_REGISTRATION = ResourceManager.ATTRIBUTE.getProperty("error.authentication");

    private Attributes() {}
}
