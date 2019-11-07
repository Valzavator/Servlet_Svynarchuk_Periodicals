package com.gmail.maxsvynarchuk.presentation.util.constants;

import com.gmail.maxsvynarchuk.util.ResourceManager;

/**
 * Relative path of pages
 */
public final class PagesPaths {
//    public static final String SITE_PREFIX = ResourceManager.CONFIGURATION.getProperty("site.prefix");
    public static final String HOME_PATH = ResourceManager.PATH.getProperty("path.home");

    public static final String SIGN_IN_PATH = ResourceManager.PATH.getProperty("path.signin");
    public static final String SIGN_UP_PATH = ResourceManager.PATH.getProperty("path.signup");
    public static final String LOGOUT_PATH = ResourceManager.PATH.getProperty("path.logout");

    private PagesPaths() {
    }
}
