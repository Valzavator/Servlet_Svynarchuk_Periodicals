package com.gmail.maxsvynarchuk.persistence.exception;

public class ConnectionException extends PersistenceException {
    private static final long serialVersionUID = -6222465065637267833L;

    public ConnectionException() {
    }

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionException(Throwable cause) {
        super(cause);
    }
}
