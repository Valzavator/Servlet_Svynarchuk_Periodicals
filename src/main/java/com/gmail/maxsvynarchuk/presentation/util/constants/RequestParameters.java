package com.gmail.maxsvynarchuk.presentation.util.constants;

import com.gmail.maxsvynarchuk.util.ResourceManager;

public final class RequestParameters {
    public static final String FIRST_NAME = ResourceManager.PARAMETER.getProperty("first.name");
    public static final String LAST_NAME = ResourceManager.PARAMETER.getProperty("last.name");
    public static final String DATE_OF_BIRTH = ResourceManager.PARAMETER.getProperty("date.of.birth");
    public static final String GENDER = ResourceManager.PARAMETER.getProperty("gender");

    public static final String EMAIL = ResourceManager.PARAMETER.getProperty("email");
    public static final String PASSWORD = ResourceManager.PARAMETER.getProperty("password");

    private RequestParameters() {
    }
}
