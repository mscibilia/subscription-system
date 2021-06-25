package com.ms.subscriptionservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfiguration {

    @Autowired
    private MessagingProperties properties;

    @Bean
    public Queue queue() {
        return new Queue(properties.getEmailNotificationQueueName());
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(properties.getExchangeName());
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(properties.getEmailNotificationRoutingKey());
    }

    @Bean
    public MessageConverter jacksonToJsonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonToJsonConverter());
        return rabbitTemplate;
    }
}
