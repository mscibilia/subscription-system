package com.ms.subscriptionservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SubscriptionNotFoundException extends Exception {

    public SubscriptionNotFoundException(String message) {
        super(message);
    }
}
