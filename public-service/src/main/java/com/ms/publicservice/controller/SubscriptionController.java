package com.ms.publicservice.controller;

import com.ms.publicservice.model.CreateSubscriptionRequestModel;
import com.ms.publicservice.model.UpdateSubscriptionRequestModel;
import com.ms.publicservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<?> createSubscription(@Valid @RequestBody CreateSubscriptionRequestModel requestModel, BindingResult result) {
        return result.hasErrors() ?
                createResponseEntityForBindingResultErrors(result) :
                subscriptionService.createSubscription(requestModel);
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
    public ResponseEntity<?> updateSubscription(@Valid @RequestBody UpdateSubscriptionRequestModel requestModel, BindingResult result) {
        return result.hasErrors() ?
                createResponseEntityForBindingResultErrors(result) :
                subscriptionService.updateSubscription(requestModel);
    }

    private ResponseEntity<String> createResponseEntityForBindingResultErrors(BindingResult result) {
        List<String> errorMessages = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        Optional<String> errorMessage = errorMessages.stream().reduce((a, b)-> a + "\n" + b);
        return new ResponseEntity<>(errorMessage.orElse("Validation failed on request body"), HttpStatus.BAD_REQUEST);
    }
}
