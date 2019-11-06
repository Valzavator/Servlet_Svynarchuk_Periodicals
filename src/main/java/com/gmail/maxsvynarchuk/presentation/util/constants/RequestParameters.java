package com.gmail.maxsvynarchuk.presentation.util.constants;

import com.gmail.maxsvynarchuk.util.ResourceManager;

public final class RequestParameters {
    public static final String EMAIL = ResourceManager.PARAMETER.getProperty("email");
    public static final String PASSWORD = ResourceManager.PARAMETER.getProperty("password");

    private RequestParameters() {
    }
}
