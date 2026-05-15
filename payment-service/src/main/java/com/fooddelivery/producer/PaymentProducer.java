package com.fooddelivery.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.config.RabbitMQConfig;
import com.fooddelivery.dto.PaymentEvent;

@Service
public class PaymentProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendPaymentEvent(
            PaymentEvent event) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.PAYMENT_ROUTING_KEY,
                event
        );
    }
}