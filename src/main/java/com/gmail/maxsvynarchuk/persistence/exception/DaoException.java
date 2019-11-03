package com.gmail.maxsvynarchuk.persistence.exception;

/**
 * Artificial exception that should be thrown out of the DAO layer
 */
public class DaoException extends PersistenceException {
    private static final long serialVersionUID = -1440038725631998471L;

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
