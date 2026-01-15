package com.spring.jwt.Subscription;

import java.util.List;

public interface SubscriptionService {

    List<SubscriptionPlan> getAllPlans();

    void activatePlan(Integer planId, String billingType);

    UserSubscription getMySubscription();
}
