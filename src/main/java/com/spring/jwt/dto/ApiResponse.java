package com.spring.jwt.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private LocalDateTime timestamp = LocalDateTime.now();
    private T data;

    public ApiResponse(T data) {
        this.data = data;
    }
}
