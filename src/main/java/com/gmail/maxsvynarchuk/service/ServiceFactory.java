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

//
//    public static CardService getCardService() {
//        return CardService.getInstance();
//    }
//
//    public static PaymentService getPaymentService() {
//        return PaymentService.getInstance();
//    }
}
