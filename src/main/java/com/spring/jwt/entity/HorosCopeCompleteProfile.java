package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "horoscope_complete_profile")
@Data
public class HorosCopeCompleteProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long horoscopeId;
}
