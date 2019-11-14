package com.gmail.maxsvynarchuk.presentation.util.constants;

import com.gmail.maxsvynarchuk.util.ResourceManager;

/**
 * Relative path of pages
 */
public final class PagesPaths {
    public static final String SITE_PREFIX = ResourceManager.PATH.getProperty("site.prefix");
    public static final String HOME_PATH = ResourceManager.PATH.getProperty("path.home");

    public static final String SIGN_IN_PATH = ResourceManager.PATH.getProperty("path.signin");
    public static final String SIGN_UP_PATH = ResourceManager.PATH.getProperty("path.signup");
    public static final String SIGN_OUT_PATH = ResourceManager.PATH.getProperty("path.signout");

    public static final String PERIODICAL_PATH = ResourceManager.PATH.getProperty("path.periodical");
    public static final String CATALOG_PATH = ResourceManager.PATH.getProperty("path.catalog");
    public static final String CART_PATH = ResourceManager.PATH.getProperty("path.cart");
    public static final String CART_ADD_ITEM_PATH = ResourceManager.PATH.getProperty("path.cart.add.item");

    public static final String ADMIN_CATALOG_PATH = ResourceManager.PATH.getProperty("path.admin.catalog");
    public static final String CREATE_PERIODICAL_PATH = ResourceManager.PATH.getProperty("path.admin.catalog.create");
    public static final String EDIT_PERIODICAL_PATH = ResourceManager.PATH.getProperty("path.admin.catalog.edit");
    public static final String CHANGE_STATUS_PERIODICAL_PATH = ResourceManager.PATH.getProperty("path.admin.catalog.change.status");

    private PagesPaths() {
    }
}
