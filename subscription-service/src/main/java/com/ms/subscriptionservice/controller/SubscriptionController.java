package com.ms.subscriptionservice.controller;

import com.ms.subscriptionservice.model.CreateSubscriptionRequestModel;
import com.ms.subscriptionservice.model.CreateSubscriptionResponseModel;
import com.ms.subscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public CreateSubscriptionResponseModel createSubscription(@Valid @RequestBody CreateSubscriptionRequestModel request) {
        return new CreateSubscriptionResponseModel(subscriptionService.createSubscription(request));
    }
}
