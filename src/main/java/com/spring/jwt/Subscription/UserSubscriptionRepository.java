package com.spring.jwt.Subscription;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Integer> {

    UserSubscription findByUserIdAndActiveTrue(Integer userId);
}

