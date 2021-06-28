package com.ms.subscriptionservice.service;

import com.ms.subscriptionservice.dto.EmailNotificationMessageDto;
import com.ms.subscriptionservice.dto.SubscriptionDto;
import com.ms.subscriptionservice.exception.IllegalCreateSubscriptionRequestException;
import com.ms.subscriptionservice.exception.SubscriptionNotFoundException;
import com.ms.subscriptionservice.messaging.EmailNotificationMessageProducer;
import com.ms.subscriptionservice.model.CreateSubscriptionRequestModel;
import com.ms.subscriptionservice.model.UpdateSubscriptionRequestModel;
import com.ms.subscriptionservice.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    private final EmailNotificationMessageProducer emailNotificationMessageProducer;

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);

    public List<SubscriptionDto> getAllSubscriptions() {
        logger.info("Retrieving all subscriptions");
        return subscriptionRepository.findAll();
    }

    public SubscriptionDto getSubscription(long subscriptionId) throws SubscriptionNotFoundException {
        logger.info("Retrieving subscription {}", subscriptionId);
        return subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionNotFoundException(String.format("Subscription id: %d was not found.", subscriptionId)));
    }

    public void deleteSubscription(long subscriptionId) {
        if(subscriptionRepository.existsById(subscriptionId)) {
            logger.info("Deleting subscription with id {}", subscriptionId);
            subscriptionRepository.deleteById(subscriptionId);
            logger.info("Subscription with id {} deleted", subscriptionId);
        }
    }

    synchronized public void updateSubscription(UpdateSubscriptionRequestModel requestModel) throws SubscriptionNotFoundException {
        Long subscriptionIdToUpdate = requestModel.getSubscriptionId();
        logger.info("Checking subscription with id {} exists", subscriptionIdToUpdate);
        if(subscriptionRepository.existsById(subscriptionIdToUpdate)) {
            logger.info("Updating subscription with id {}", subscriptionIdToUpdate);
            SubscriptionDto subscriptionDto = SubscriptionDto
                    .builder()
                    .id(requestModel.getSubscriptionId())
                    .emailAddress(requestModel.getEmailAddress())
                    .dateOfBirth(requestModel.getDateOfBirth())
                    .firstName(requestModel.getFirstName())
                    .gender(requestModel.getGender())
                    .newsletterId(requestModel.getNewsletterId())
                    .consentFlag(requestModel.getConsentFlag())
                    .build();

            subscriptionRepository.save(subscriptionDto);
            logger.info("Subscription with id {} updated", subscriptionIdToUpdate);
            emailNotificationMessageProducer
                    .sendEmailNotification(new EmailNotificationMessageDto(subscriptionDto.getEmailAddress(), "Your subscription was updated"));
        } else {
            throw new SubscriptionNotFoundException(String.format("Cannot update subscription with id %s as it does not exist.", subscriptionIdToUpdate));
        }
    }

    synchronized public Long createSubscription(CreateSubscriptionRequestModel requestModel) throws IllegalCreateSubscriptionRequestException {
        logger.info("Checking if subscription with email address {} already exists before creating subscription", requestModel.getEmailAddress());
        if (subscriptionRepository.findByEmailAddress(requestModel.getEmailAddress()).isPresent()) {
            throw new IllegalCreateSubscriptionRequestException(
                    String.format("Cannot create subscription with email address %s since one with" +
                            " that email address already exists", requestModel.getEmailAddress())
            );
        }

        logger.info("Creating subscription: {}", requestModel.toString());
        SubscriptionDto subscriptionDto = SubscriptionDto
                .builder()
                .emailAddress(requestModel.getEmailAddress())
                .dateOfBirth(requestModel.getDateOfBirth())
                .firstName(requestModel.getFirstName())
                .gender(requestModel.getGender())
                .newsletterId(requestModel.getNewsletterId())
                .consentFlag(requestModel.getConsentFlag())
                .build();

        SubscriptionDto savedSubscriptionDto = subscriptionRepository.save(subscriptionDto);
        logger.info("Subscription persisted with id {}", savedSubscriptionDto.getId());

        emailNotificationMessageProducer
                .sendEmailNotification(new EmailNotificationMessageDto(savedSubscriptionDto.getEmailAddress(), "Your subscription was created"));

        return savedSubscriptionDto.getId();
    }

}
