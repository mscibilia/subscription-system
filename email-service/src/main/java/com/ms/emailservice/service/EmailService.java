package com.ms.emailservice.service;

import com.ms.emailservice.dto.EmailNotificationMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendEmail(EmailNotificationMessageDto emailNotification) {
        logger.info("Sending email to {} with message {}", emailNotification.getEmailAddress(), emailNotification.getEmailMessage());
    }
}
