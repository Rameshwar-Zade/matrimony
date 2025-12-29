package com.spring.jwt.exception;

public class PartnerExpectationNotFoundException extends RuntimeException {
    public PartnerExpectationNotFoundException(String msg) {
        super(msg);
    }
}
