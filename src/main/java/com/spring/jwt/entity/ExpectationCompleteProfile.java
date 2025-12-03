package com.spring.jwt.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "expectations_complete_profile")
@Getter
@Setter
@NoArgsConstructor
public class ExpectationCompleteProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;

    private Long horoscopeId;

    private Long partnerExpectationId;
}
