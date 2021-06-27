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
    public ResponseEntity<?> createSubscription(@Valid @RequestBody CreateSubscriptionRequestModel requestModel, @RequestHeader("authorization") String authorization) {
        return subscriptionService.createSubscription(requestModel, authorization);
    }

    @GetMapping
    public ResponseEntity<?> getAllSubscriptions(@RequestHeader("authorization") String authorization) {
        return subscriptionService.getAllSubscriptions(authorization);
    }

    @GetMapping(path = "/{subscriptionId}")
    public ResponseEntity<?> getSubscription(@PathVariable long subscriptionId, @RequestHeader("authorization") String authorization) {
        return subscriptionService.getSubscription(subscriptionId, authorization);
    }

    @DeleteMapping(path = "/{subscriptionId}")
    public ResponseEntity<?> deleteSubscription(@PathVariable long subscriptionId, @RequestHeader("authorization") String authorization) {
        return subscriptionService.deleteSubscription(subscriptionId, authorization);
    }

    @PutMapping
    public ResponseEntity<?> updateSubscription(@Valid @RequestBody UpdateSubscriptionRequestModel requestModel, @RequestHeader("authorization") String authorization) {
        return subscriptionService.updateSubscription(requestModel, authorization);
    }
}
