package com.gmail.maxsvynarchuk.presentation.util.constants;

import com.gmail.maxsvynarchuk.util.ResourceManager;

/**
 * Path to jsp
 */
public final class Views {
    public static final String FOLDER = ResourceManager.VIEW.getProperty("view.folder");

    public static final String HOME_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.home");
    public static final String SIGN_IN_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.signin");
    public static final String SIGN_UP_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.signup");

    public static final String PERIODICAL_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.periodical");
    public static final String CATALOG_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.catalog");
    public static final String CART_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.cart");

    public static final String ERROR_404_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.error.404");

    private Views() {
    }
}
