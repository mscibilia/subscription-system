package com.ms.subscriptionservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "messaging")
public class MessagingProperties {
    private String exchangeName;
    private String emailNotificationQueueName;
    private String emailNotificationRoutingKey;
}
