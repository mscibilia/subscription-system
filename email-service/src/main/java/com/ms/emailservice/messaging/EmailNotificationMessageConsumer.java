package com.ms.emailservice.messaging;

import com.ms.emailservice.dto.EmailNotificationMessageDto;
import com.ms.emailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailNotificationMessageConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = "${messaging.email-notification-queue-name}")
    public void consumeEmailNotificationMessage(EmailNotificationMessageDto emailNotification) {
        emailService.sendEmail(emailNotification);
    }
}
