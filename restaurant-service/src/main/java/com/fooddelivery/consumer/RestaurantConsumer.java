package com.fooddelivery.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.config.RabbitMQConfig;
import com.fooddelivery.dto.PaymentEvent;
import com.fooddelivery.service.RestaurantService;

@Service
public class RestaurantConsumer {

    @Autowired
    private RestaurantService restaurantService;

    @RabbitListener(
            queues = RabbitMQConfig.RESTAURANT_QUEUE
    )
    public void consumePaymentEvent(
            PaymentEvent event) {

        System.out.println(
                "Received Payment Event For Order: "
                        + event.getOrderId()
        );

        restaurantService
                .processRestaurantOrder(event);
    }
}