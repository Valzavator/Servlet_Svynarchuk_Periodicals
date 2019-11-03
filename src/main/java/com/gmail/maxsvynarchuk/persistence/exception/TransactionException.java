package com.gmail.maxsvynarchuk.persistence.exception;

public class TransactionException extends PersistenceException {
    private static final long serialVersionUID = 7584032696542742042L;

    public TransactionException() {
    }

    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionException(Throwable cause) {
        super(cause);
    }
}
