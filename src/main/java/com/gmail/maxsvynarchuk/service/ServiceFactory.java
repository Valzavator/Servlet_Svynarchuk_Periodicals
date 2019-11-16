package com.gmail.maxsvynarchuk.service;

/**
 * Intermediate layer between command layer and dao layer.
 * Implements operations of finding, creating, deleting entities.
 * Uses dao layer.
 *
 * @author Maksym Svynarchuk
 */
public class ServiceFactory {
    public static UserService getUserService() {
        return UserService.getInstance();
    }

    public static PeriodicalService getPeriodicalService() {
        return PeriodicalService.getInstance();
    }

    public static SubscriptionService getSubscriptionService() {
        return SubscriptionService.getInstance();
    }

    public static IssueService getIssueService() {
        return IssueService.getInstance();
    }

    public static ShoppingCartService getShoppingCartService() {
        return ShoppingCartService.getInstance();
    }
}
