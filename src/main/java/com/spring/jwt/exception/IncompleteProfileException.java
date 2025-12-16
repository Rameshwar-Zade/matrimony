package com.spring.jwt.exception;

public class IncompleteProfileException extends RuntimeException {
    public IncompleteProfileException(String message) {

        super(message);
    }
}
