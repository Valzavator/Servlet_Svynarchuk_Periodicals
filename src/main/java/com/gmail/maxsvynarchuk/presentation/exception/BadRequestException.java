package com.gmail.maxsvynarchuk.presentation.exception;

public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = -1273020062207179972L;

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }
}
