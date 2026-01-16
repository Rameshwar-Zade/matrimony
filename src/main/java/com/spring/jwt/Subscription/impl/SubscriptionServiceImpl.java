package com.spring.jwt.Subscription.impl;

import com.spring.jwt.Subscription.*;

import com.spring.jwt.jwt.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionPlanRepository planRepository;

    @Autowired
    private UserSubscriptionRepository subscriptionRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public List<SubscriptionPlan> getAllPlans() {
        return planRepository.findByActiveTrue();
    }

    @Override
    public void activatePlan(Integer planId, String billingType) {

        Integer userId = jwtService.extractUserId(jwtService.extractToken());

        SubscriptionPlan plan =
                planRepository.findByIdAndActiveTrue(planId);

        if (plan == null) {
            throw new RuntimeException("Invalid plan");
        }

        // deactivate old subscription
        UserSubscription old =
                subscriptionRepository.findByUserIdAndActiveTrue(userId);

        if (old != null) {
            old.setActive(false);
            subscriptionRepository.save(old);
        }

        UserSubscription sub = new UserSubscription();
        sub.setUserId(userId);
        sub.setPlanId(planId);
        sub.setBillingType(billingType);
        sub.setStartDate(LocalDateTime.now());

        if ("YEARLY".equalsIgnoreCase(billingType)) {
            sub.setEndDate(LocalDateTime.now().plusYears(1));
        } else {
            sub.setEndDate(LocalDateTime.now().plusMonths(1));
        }

        sub.setActive(true);

        subscriptionRepository.save(sub);
    }

    @Override
    public UserSubscription getMySubscription() {
        Integer userId = jwtService.extractUserId(jwtService.extractToken());
        return subscriptionRepository.findByUserIdAndActiveTrue(userId);
    }
}

