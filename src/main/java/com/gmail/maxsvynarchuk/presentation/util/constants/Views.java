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

    public static final String PROFILE_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.profile");
    public static final String PERIODICAL_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.periodical");
    public static final String CATALOG_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.catalog");

    public static final String CART_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.cart");
    public static final String SUBSCRIPTIONS_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.subscriptions");

    public static final String ADMIN_CATALOG_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.admin.catalog");
    public static final String CREATE_PERIODICAL_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.create.periodical");
    public static final String EDIT_PERIODICAL_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.edit.periodical");
    public static final String CREATE_ISSUE_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.create.issue");
    public static final String PAYMENTS_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.payments");

    public static final String ERROR_403_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.error.403");
    public static final String ERROR_404_VIEW = FOLDER + ResourceManager.VIEW.getProperty("view.error.404");

    private Views() {
    }
}
