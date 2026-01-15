package com.spring.jwt.Subscription;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subscription_plans")
@Getter
@Setter
public class SubscriptionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String planName;        // BASIC, PREMIUM, ELITE, PLATINUM

    private Integer monthlyPrice;   // 15, 39, 79, 159

    private Integer yearlyPrice;    // optional (discounted)

    private Boolean mostPopular;    // true for PREMIUM

    private Boolean active;
}
