package com.ms.subscriptionservice.service;

import com.ms.subscriptionservice.dto.SubscriptionDto;
import com.ms.subscriptionservice.model.CreateSubscriptionRequestModel;
import com.ms.subscriptionservice.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    Logger logger = LoggerFactory.getLogger(SubscriptionService.class);

    public Long createSubscription(CreateSubscriptionRequestModel requestModel){
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

        return savedSubscriptionDto.getId();
    }

}
