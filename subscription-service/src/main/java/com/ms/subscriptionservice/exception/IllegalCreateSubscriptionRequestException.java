package com.ms.subscriptionservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalCreateSubscriptionRequestException extends Exception {

    public IllegalCreateSubscriptionRequestException(String message) {
        super(message);
    }
}
