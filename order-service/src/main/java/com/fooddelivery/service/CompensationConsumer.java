package com.fooddelivery.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.config.RabbitMQConfig;
import com.fooddelivery.dto.CompensationEvent;

@Service
public class CompensationConsumer {

    @Autowired
    private OrderCompensationService
            compensationService;

    @RabbitListener(
            queues =
            RabbitMQConfig.COMPENSATION_QUEUE
    )
    public void consumeCompensationEvent(
            CompensationEvent event) {

        compensationService
                .cancelOrder(event.getOrderId());
    }
}
