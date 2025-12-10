package com.spring.jwt.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class ResponseDto {
    public String status;
    public String message;
    private String data;


    public ResponseDto(String status, String message) {
        this.status=status;
        this.message=message;
    }
    public ResponseDto() {

    }
    public ResponseDto(String status, String message, String data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}