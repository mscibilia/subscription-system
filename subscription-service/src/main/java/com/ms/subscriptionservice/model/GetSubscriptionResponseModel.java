package com.ms.subscriptionservice.model;

import com.ms.subscriptionservice.dto.SubscriptionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetSubscriptionResponseModel {
    private final SubscriptionDto subscription;
}
