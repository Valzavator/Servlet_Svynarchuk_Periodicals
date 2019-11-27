package com.gmail.maxsvynarchuk.service.exception;

/**
 * Artificial exception that should be thrown out of the Service layer
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -2097098162974144614L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
