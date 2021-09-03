package com.enctor.booking.exception;

public class ValidationException extends Exception {
    private static final long serialVersionUID = 1660584430966904363L;

    public ValidationException(String message) {
        super(message);
    }
}
