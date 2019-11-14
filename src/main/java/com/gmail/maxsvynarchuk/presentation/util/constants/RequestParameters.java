package com.gmail.maxsvynarchuk.presentation.util.constants;

import com.gmail.maxsvynarchuk.util.ResourceManager;

public final class RequestParameters {
    public static final String USER_FIRST_NAME = ResourceManager.PARAMETER.getProperty("user.first.name");
    public static final String USER_LAST_NAME = ResourceManager.PARAMETER.getProperty("user.last.name");
    public static final String USER_DATE_OF_BIRTH = ResourceManager.PARAMETER.getProperty("user.date.of.birth");
    public static final String USER_GENDER = ResourceManager.PARAMETER.getProperty("user.gender");
    public static final String USER_EMAIL = ResourceManager.PARAMETER.getProperty("user.email");
    public static final String USER_PASSWORD = ResourceManager.PARAMETER.getProperty("user.password");

    public static final String PERIODICAL_NAME = ResourceManager.PARAMETER.getProperty("periodical.name");
    public static final String PERIODICAL_STATUS = ResourceManager.PARAMETER.getProperty("periodical.status");
    public static final String PERIODICAL_DESCRIPTION = ResourceManager.PARAMETER.getProperty("periodical.description");
    public static final String PERIODICAL_PRICE = ResourceManager.PARAMETER.getProperty("periodical.price");
    public static final String PERIODICAL_TYPE_ID = ResourceManager.PARAMETER.getProperty("periodical.type.id");
    public static final String PERIODICAL_FREQUENCY_ID = ResourceManager.PARAMETER.getProperty("periodical.frequency.id");
    public static final String PERIODICAL_PUBLISHER_ID = ResourceManager.PARAMETER.getProperty("periodical.publisher.id");

    public static final String PERIODICAL_ID = ResourceManager.PARAMETER.getProperty("periodical.id");
    public static final String SUBSCRIPTION_PLAN_ID = ResourceManager.PARAMETER.getProperty("subscription.plan.id");

    public static final String PAGINATION_PAGE = ResourceManager.PARAMETER.getProperty("pagination.page");

    public static final String ERROR_ATTRIBUTE = ResourceManager.PARAMETER.getProperty("error.attribute");

    private RequestParameters() {
    }
}
