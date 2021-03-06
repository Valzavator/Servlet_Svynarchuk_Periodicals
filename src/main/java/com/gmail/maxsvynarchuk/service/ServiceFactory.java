package com.gmail.maxsvynarchuk.service;

/**
 * Factory that returns Service entities
 *
 * @author Maksym Svynarhcuk
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

    public static PaymentService getPaymentService() {
        return PaymentService.getInstance();
    }
}
