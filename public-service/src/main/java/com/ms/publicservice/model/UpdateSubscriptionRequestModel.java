package com.ms.publicservice.model;

import lombok.Data;

@Data
public class UpdateSubscriptionRequestModel extends CreateSubscriptionRequestModel {
    private Long subscriptionId;
}
