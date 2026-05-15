package com.fooddelivery.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.config.RabbitMQConfig;
import com.fooddelivery.dto.OrderEvent;
import com.fooddelivery.service.PaymentService;

@Service
public class PaymentConsumer {

    @Autowired
    private PaymentService paymentService;

    @RabbitListener(
            queues = RabbitMQConfig.PAYMENT_QUEUE
    )
    public void consumeOrderEvent(
            OrderEvent event) {

        System.out.println(
                "Received Order Event: "
                        + event.getOrderId()
        );

        paymentService.processPayment(event);
    }
}
