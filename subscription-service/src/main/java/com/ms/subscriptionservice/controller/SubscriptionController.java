package com.ms.subscriptionservice.controller;

import com.ms.subscriptionservice.model.CreateSubscriptionRequestModel;
import com.ms.subscriptionservice.model.CreateSubscriptionResponseModel;
import com.ms.subscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller("/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public CreateSubscriptionResponseModel createSubscription(@RequestBody CreateSubscriptionRequestModel request) {
        return new CreateSubscriptionResponseModel(subscriptionService.createSubscription(request));
    }
}
