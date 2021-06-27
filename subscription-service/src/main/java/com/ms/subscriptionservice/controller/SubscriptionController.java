package com.ms.subscriptionservice.controller;

import com.ms.subscriptionservice.exception.SubscriptionNotFoundException;
import com.ms.subscriptionservice.model.*;
import com.ms.subscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping(path = "/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CreateSubscriptionResponseModel createSubscription(@Valid @RequestBody CreateSubscriptionRequestModel requestModel) {
        return new CreateSubscriptionResponseModel(subscriptionService.createSubscription(requestModel));
    }

    @GetMapping
    public GetAllSubscriptionsResponseModel getAllSubscriptions() {
        return new GetAllSubscriptionsResponseModel(subscriptionService.getAllSubscriptions());
    }

    @GetMapping(path = "/{subscriptionId}")
    public GetSubscriptionResponseModel getSubscription(@PathVariable long subscriptionId) throws SubscriptionNotFoundException {
        return new GetSubscriptionResponseModel(subscriptionService.getSubscription(subscriptionId));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{subscriptionId}")
    public void deleteSubscription(@PathVariable long subscriptionId) {
        subscriptionService.deleteSubscription(subscriptionId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public void updateSubscription(@Valid @RequestBody UpdateSubscriptionRequestModel requestModel) throws SubscriptionNotFoundException {
        subscriptionService.updateSubscription(requestModel);
    }
}
