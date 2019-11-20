package com.gmail.maxsvynarchuk.presentation.util.constants;

import com.gmail.maxsvynarchuk.util.ResourceManager;

/**
 * Relative path of pages
 */
public final class PagesPaths {
    public static final String SITE_PREFIX = ResourceManager.PATH.getProperty("site.prefix");

    public static final String SIGN_IN_PATH = ResourceManager.PATH.getProperty("path.signin");
    public static final String SIGN_UP_PATH = ResourceManager.PATH.getProperty("path.signup");

    public static final String HOME_PATH = ResourceManager.PATH.getProperty("path.home");
    public static final String PERIODICAL_PATH = ResourceManager.PATH.getProperty("path.periodical");
    public static final String CATALOG_PATH = ResourceManager.PATH.getProperty("path.catalog");

    public static final String PROFILE_PATH = ResourceManager.PATH.getProperty("path.profile");
    public static final String SIGN_OUT_PATH = ResourceManager.PATH.getProperty("path.signout");

    public static final String CART_PATH = ResourceManager.PATH.getProperty("path.cart");
    public static final String CART_ADD_ITEM_PATH = ResourceManager.PATH.getProperty("path.cart.add.item");
    public static final String CART_REMOVE_ITEM_PATH = ResourceManager.PATH.getProperty("path.cart.remove.item");
    public static final String CART_REMOVE_ALL_ITEM_PATH = ResourceManager.PATH.getProperty("path.cart.remove.all.item");
    public static final String CART_SUBSCRIPTION_PAYMENT_PATH = ResourceManager.PATH.getProperty("path.cart.subscription.payment");
    public static final String SUBSCRIPTIONS_PATH = ResourceManager.PATH.getProperty("path.user.subscriptions");

    public static final String ADMIN_CATALOG_PATH = ResourceManager.PATH.getProperty("path.admin.catalog");
    public static final String CREATE_PERIODICAL_PATH = ResourceManager.PATH.getProperty("path.admin.catalog.periodical.create");
    public static final String EDIT_PERIODICAL_PATH = ResourceManager.PATH.getProperty("path.admin.catalog.periodical.edit");
    public static final String CHANGE_STATUS_PERIODICAL_PATH = ResourceManager.PATH.getProperty("path.admin.catalog.change.status");
    public static final String CREATE_ISSUE_PATH = ResourceManager.PATH.getProperty("path.admin.issue.create");
    public static final String PAYMENTS_PATH = ResourceManager.PATH.getProperty("path.admin.payments");
    public static final String PAYMENT_OVERVIEW_PATH = ResourceManager.PATH.getProperty("path.admin.payment");
    public static final String USER_PATH = ResourceManager.PATH.getProperty("path.admin.user");

    private PagesPaths() {
    }
}
