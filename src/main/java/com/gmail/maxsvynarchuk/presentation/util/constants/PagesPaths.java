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
    public static final String SIGN_OUT_PATH = ResourceManager.PATH.getProperty("path.signout");

    public static final String CATALOG_PATH = ResourceManager.PATH.getProperty("path.catalog");
    public static final String CART_ADD_ITEM_PATH = ResourceManager.PATH.getProperty("path.cart.add.item");

    private PagesPaths() {
    }
}
