package com.gmail.maxsvynarchuk.presentation.util.constants;

import com.gmail.maxsvynarchuk.util.ResourceManager;

/**
 * Path to jsp
 */
public final class Views {
    public static final String FOLDER = ResourceManager.VIEW.getProperty("view.folder");

    public static final String HOME_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.home");
    public static final String LOGIN_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.login");
    public static final String SIGN_UP_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.signup");

    private Views() {
    }
}
