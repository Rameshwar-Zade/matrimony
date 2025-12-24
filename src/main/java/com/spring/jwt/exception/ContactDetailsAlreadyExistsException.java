package com.spring.jwt.exception;

public class ContactDetailsAlreadyExistsException extends RuntimeException {
    public ContactDetailsAlreadyExistsException(String message) {
        super(message);
    }
}
