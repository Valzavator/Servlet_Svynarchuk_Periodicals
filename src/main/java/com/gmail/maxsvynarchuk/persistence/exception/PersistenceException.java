package com.gmail.maxsvynarchuk.persistence.exception;

/**
 * Artificial exception that should be thrown out of the persistence layer
 */
public class PersistenceException extends RuntimeException {
    private static final long serialVersionUID = 1910976280321561949L;

    public PersistenceException() {
    }

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceException(Throwable cause) {
        super(cause);
    }
}
