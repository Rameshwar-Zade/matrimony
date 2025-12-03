package com.spring.jwt.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoroscopeDetailsRequestDTO {

    private String dob;       // yyyy-mm-dd
    private String time;
    private String birthPlace;
    private String rashi;
    private String nakshatra;
    private String charan;
    private String nadi;
    private String gan;
    private String mangal;
    private String gotra;
    private String devak;
    private String status1;
    private Integer userId;
}


