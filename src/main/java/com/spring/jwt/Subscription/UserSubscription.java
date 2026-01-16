package com.spring.jwt.Subscription;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_subscriptions")
@Getter
@Setter
public class UserSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer planId;

    private String billingType; // MONTHLY / YEARLY

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Boolean active;
}
