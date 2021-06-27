package com.ms.subscriptionservice.service;

import com.ms.subscriptionservice.dto.EmailNotificationMessageDto;
import com.ms.subscriptionservice.dto.SubscriptionDto;
import com.ms.subscriptionservice.exception.IllegalCreateSubscriptionRequestException;
import com.ms.subscriptionservice.exception.SubscriptionNotFoundException;
import com.ms.subscriptionservice.messaging.EmailNotificationMessageProducer;
import com.ms.subscriptionservice.model.CreateSubscriptionRequestModel;
import com.ms.subscriptionservice.model.Gender;
import com.ms.subscriptionservice.model.UpdateSubscriptionRequestModel;
import com.ms.subscriptionservice.repository.SubscriptionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubscriptionServiceTest {

    @Mock
    private SubscriptionRepository mockSubscriptionRepository;

    @Mock
    private EmailNotificationMessageProducer mockEmailNotificationMessageProducer;

    @Test
    public void shouldThrowIllegalCreateSubscriptionRequestExceptionWhenCreatingSubscriptionWithExistingEmail() {
        CreateSubscriptionRequestModel requestModel = buildCreateSubscriptionRequestModel();

        when(mockSubscriptionRepository.findByEmailAddress(anyString()))
                .thenReturn(Optional.of(SubscriptionDto.builder()
                        .emailAddress(requestModel.getEmailAddress())
                        .build()));

        SubscriptionService sut = new SubscriptionService(mockSubscriptionRepository, mockEmailNotificationMessageProducer);

        assertThrows(IllegalCreateSubscriptionRequestException.class, () -> sut.createSubscription(requestModel));
    }

    @Test
    public void shouldSendEmailNotificationWhenSubscriptionIsCreated() throws IllegalCreateSubscriptionRequestException {
        CreateSubscriptionRequestModel requestModel = buildCreateSubscriptionRequestModel();

        when(mockSubscriptionRepository.findByEmailAddress(anyString()))
                .thenReturn(Optional.empty());

        when(mockSubscriptionRepository.save(any(SubscriptionDto.class))).thenReturn(SubscriptionDto.builder()
                .emailAddress(requestModel.getEmailAddress())
                .build());

        SubscriptionService sut = new SubscriptionService(mockSubscriptionRepository, mockEmailNotificationMessageProducer);

        sut.createSubscription(requestModel);

        verify(mockSubscriptionRepository).save(any(SubscriptionDto.class));
        verify(mockEmailNotificationMessageProducer).sendEmailNotification(any(EmailNotificationMessageDto.class));
    }

    @Test
    public void shouldThrowSubscriptionNotFoundExceptionWhenUpdatingSubscriptionThatDoesNotExist() {
        UpdateSubscriptionRequestModel requestModel = new UpdateSubscriptionRequestModel();
        requestModel.setSubscriptionId(1L);

        when(mockSubscriptionRepository.existsById(anyLong())).thenReturn(false);

        SubscriptionService sut = new SubscriptionService(mockSubscriptionRepository, mockEmailNotificationMessageProducer);

        assertThrows(SubscriptionNotFoundException.class, () -> sut.updateSubscription(requestModel));
    }

    @Test
    public void shouldSendEmailNotificationWhenSubscriptionIsUpdated() throws SubscriptionNotFoundException {
        UpdateSubscriptionRequestModel requestModel = new UpdateSubscriptionRequestModel();
        requestModel.setSubscriptionId(1L);

        when(mockSubscriptionRepository.existsById(anyLong())).thenReturn(true);

        when(mockSubscriptionRepository.save(any(SubscriptionDto.class))).thenReturn(SubscriptionDto.builder()
                .emailAddress(requestModel.getEmailAddress())
                .build());

        SubscriptionService sut = new SubscriptionService(mockSubscriptionRepository, mockEmailNotificationMessageProducer);

        sut.updateSubscription(requestModel);

        verify(mockSubscriptionRepository).save(any(SubscriptionDto.class));
        verify(mockEmailNotificationMessageProducer).sendEmailNotification(any(EmailNotificationMessageDto.class));
    }

    @Test
    public void shouldThrowSubscriptionNotFoundExceptionWhenGettingSubscriptionThatDoesNotExist() {
        when(mockSubscriptionRepository.findById(anyLong())).thenReturn(Optional.empty());
        SubscriptionService sut = new SubscriptionService(mockSubscriptionRepository, mockEmailNotificationMessageProducer);
        assertThrows(SubscriptionNotFoundException.class, () -> sut.getSubscription(1L));
    }

    @Test
    public void shouldDeleteSubscriptionWhenSubscriptionWithGivenIdExists() {
        when(mockSubscriptionRepository.existsById(anyLong())).thenReturn(true);
        SubscriptionService sut = new SubscriptionService(mockSubscriptionRepository, mockEmailNotificationMessageProducer);

        long subscriptionId = 1L;
        sut.deleteSubscription(subscriptionId);
        verify(mockSubscriptionRepository).deleteById(subscriptionId);
    }

    @Test
    public void shouldNotDeleteSubscriptionWhenSubscriptionWithGivenIdDoesNotExist() {
        when(mockSubscriptionRepository.existsById(anyLong())).thenReturn(false);
        SubscriptionService sut = new SubscriptionService(mockSubscriptionRepository, mockEmailNotificationMessageProducer);

        long subscriptionId = 1L;
        sut.deleteSubscription(subscriptionId);
        verify(mockSubscriptionRepository, never()).deleteById(anyLong());
    }

    private CreateSubscriptionRequestModel buildCreateSubscriptionRequestModel() {
        CreateSubscriptionRequestModel requestModel = new CreateSubscriptionRequestModel();
        requestModel.setEmailAddress("test@test.com.au");
        requestModel.setFirstName("John");
        requestModel.setNewsletterId("nl1");
        requestModel.setGender(Gender.MALE);
        requestModel.setConsentFlag(true);
        requestModel.setDateOfBirth(LocalDate.now());
        return requestModel;
    }

}
