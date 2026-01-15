package com.spring.jwt.Subscription;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    private final SubscriptionService service;

    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    @GetMapping("/plans")
    public List<SubscriptionPlan> plans() {
        return service.getAllPlans();
    }

    @PostMapping("/activate")
    public String activate(@RequestParam Integer planId, @RequestParam String billingType) {

        service.activatePlan(planId, billingType);
        return "Subscription Activated";
    }

    @GetMapping("/me")
    public UserSubscription mySubscription() {
        return service.getMySubscription();
    }
}

