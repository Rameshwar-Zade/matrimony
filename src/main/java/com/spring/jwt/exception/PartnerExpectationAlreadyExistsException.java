package com.spring.jwt.exception;


public class PartnerExpectationAlreadyExistsException extends RuntimeException {
    public PartnerExpectationAlreadyExistsException(String message) {
        super(message);
    }
}
