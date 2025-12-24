package com.spring.jwt.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
    MALE,
    FEMALE;

    @JsonCreator
    public static Gender from(String value) {
        if (value == null) {
            return null;
        }
        return Gender.valueOf(value.trim().toUpperCase());
    }
}
