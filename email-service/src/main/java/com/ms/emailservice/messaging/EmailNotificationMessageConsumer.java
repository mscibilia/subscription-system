package com.ms.emailservice.messaging;

import com.ms.emailservice.dto.EmailNotificationMessageDto;
import com.ms.emailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@RabbitListener(queues = "${message.email.notification.queue.name}")
public class EmailNotificationMessageConsumer {

    private final EmailService emailService;

    @RabbitHandler
    public void consumeEmailNotificationMessage(EmailNotificationMessageDto emailNotification) {
        emailService.sendEmail(emailNotification);
    }
}
