package com.ms.subscriptionservice.messaging;

import com.ms.subscriptionservice.config.MessagingProperties;
import com.ms.subscriptionservice.dto.EmailNotificationMessageDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailNotificationMessageProducer {

    private final RabbitTemplate rabbitTemplate;
    private final MessagingProperties messagingProperties;

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationMessageProducer.class);

    public void sendEmailNotification(EmailNotificationMessageDto emailNotificationMessageDto) {
        logger.info("Sending email notification to queue: {} ", emailNotificationMessageDto.toString());

        rabbitTemplate.convertAndSend(messagingProperties.getExchangeName(),
                messagingProperties.getEmailNotificationRoutingKey(),
                emailNotificationMessageDto);

        logger.info("Email notification sent");
    }
}
