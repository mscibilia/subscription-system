package com.ms.publicservice.controller;

import com.ms.publicservice.model.CreateSubscriptionRequestModel;
import com.ms.publicservice.model.UpdateSubscriptionRequestModel;
import com.ms.publicservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(path = "/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<?> createSubscription(@Valid @RequestBody CreateSubscriptionRequestModel requestModel) {
        return subscriptionService.createSubscription(requestModel);
    }

    @GetMapping
    public ResponseEntity<?> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    @GetMapping(path = "/{subscriptionId}")
    public ResponseEntity<?> getSubscription(@PathVariable long subscriptionId) {
        return subscriptionService.getSubscription(subscriptionId);
    }

    @DeleteMapping(path = "/{subscriptionId}")
    public ResponseEntity<?> deleteSubscription(@PathVariable long subscriptionId) {
        return subscriptionService.deleteSubscription(subscriptionId);
    }

    @PutMapping
    public ResponseEntity<?> updateSubscription(@Valid @RequestBody UpdateSubscriptionRequestModel requestModel) {
        return subscriptionService.updateSubscription(requestModel);
    }
}
