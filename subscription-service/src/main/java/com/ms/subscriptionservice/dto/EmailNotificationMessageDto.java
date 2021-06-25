package com.ms.subscriptionservice.dto;

import lombok.Value;

@Value
public class EmailNotificationMessageDto {
    private String emailAddress;
    private String emailMessage;
}
