package com.ms.subscriptionservice.controller;

import com.ms.subscriptionservice.exception.IllegalCreateSubscriptionRequestException;
import com.ms.subscriptionservice.exception.SubscriptionNotFoundException;
import com.ms.subscriptionservice.model.*;
import com.ms.subscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<?> createSubscription(@Valid @RequestBody CreateSubscriptionRequestModel requestModel, BindingResult result) {
        try {
            return result.hasErrors() ?
                    createResponseEntityForBindingResultErrors(result) :
                    ResponseEntity.status(HttpStatus.CREATED)
                            .body(new CreateSubscriptionResponseModel(subscriptionService.createSubscription(requestModel)));
        } catch (IllegalCreateSubscriptionRequestException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<GetAllSubscriptionsResponseModel> getAllSubscriptions() {
        return ResponseEntity.ok(new GetAllSubscriptionsResponseModel(subscriptionService.getAllSubscriptions()));
    }

    @GetMapping(path = "/{subscriptionId}")
    public ResponseEntity<?> getSubscription(@PathVariable long subscriptionId) {
        try {
            return ResponseEntity.ok(new GetSubscriptionResponseModel(subscriptionService.getSubscription(subscriptionId)));
        } catch (SubscriptionNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{subscriptionId}")
    public ResponseEntity<?> deleteSubscription(@PathVariable long subscriptionId) {
        subscriptionService.deleteSubscription(subscriptionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<?> updateSubscription(@Valid @RequestBody UpdateSubscriptionRequestModel requestModel, BindingResult result) {
        try {
            subscriptionService.updateSubscription(requestModel);
            return result.hasErrors() ?
                    createResponseEntityForBindingResultErrors(result) :
                    ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (SubscriptionNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private ResponseEntity<String> createResponseEntityForBindingResultErrors(BindingResult result) {
        List<String> errorMessages = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        Optional<String> errorMessage = errorMessages.stream().reduce((a, b)-> a + "\n" + b);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorMessage.orElse("Validation failed on request body"));
    }
}
