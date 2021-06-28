package com.ms.subscriptionservice.controller;

import com.ms.subscriptionservice.exception.IllegalCreateSubscriptionRequestException;
import com.ms.subscriptionservice.exception.SubscriptionNotFoundException;
import com.ms.subscriptionservice.model.*;
import com.ms.subscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CreateSubscriptionResponseModel createSubscription(@Valid @RequestBody CreateSubscriptionRequestModel requestModel) {
        try {
            return new CreateSubscriptionResponseModel(subscriptionService.createSubscription(requestModel));
        } catch (IllegalCreateSubscriptionRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping
    public GetAllSubscriptionsResponseModel getAllSubscriptions() {
        return new GetAllSubscriptionsResponseModel(subscriptionService.getAllSubscriptions());
    }

    @GetMapping(path = "/{subscriptionId}")
    public GetSubscriptionResponseModel getSubscription(@PathVariable long subscriptionId) {
        try {
            return new GetSubscriptionResponseModel(subscriptionService.getSubscription(subscriptionId));
        } catch (SubscriptionNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping(path = "/{subscriptionId}")
    public ResponseEntity<?> deleteSubscription(@PathVariable long subscriptionId) {
        subscriptionService.deleteSubscription(subscriptionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<?> updateSubscription(@Valid @RequestBody UpdateSubscriptionRequestModel requestModel) {
        try {
            subscriptionService.updateSubscription(requestModel);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (SubscriptionNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
