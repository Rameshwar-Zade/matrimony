package com.spring.jwt.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDetailsDTO {
    private String fullAddress;
    private String city;
    private Integer pinCode;
    private Long moNumber;
    private Integer alternateNumber;
}
