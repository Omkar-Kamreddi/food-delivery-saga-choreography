package com.fooddelivery.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.config.RabbitMQConfig;
import com.fooddelivery.dto.RestaurantEvent;

@Service
public class RestaurantProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //here i produce restaurant result to back 
    public void sendRestaurantEvent(
            RestaurantEvent event) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.RESTAURANT_ROUTING_KEY,
                event
        );
    }
}
