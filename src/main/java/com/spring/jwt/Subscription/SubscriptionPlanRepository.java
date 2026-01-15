package com.spring.jwt.Subscription;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Integer> {

    List<SubscriptionPlan> findByActiveTrue();

    SubscriptionPlan findByIdAndActiveTrue(Integer id);
}

