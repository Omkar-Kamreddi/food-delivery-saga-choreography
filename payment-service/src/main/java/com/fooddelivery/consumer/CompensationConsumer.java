package com.fooddelivery.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.config.RabbitMQConfig;
import com.fooddelivery.dto.RestaurantEvent;
import com.fooddelivery.service.CompensationService;

@Service
public class CompensationConsumer {

    @Autowired
    private CompensationService compensationService;

    @RabbitListener(
            queues = RabbitMQConfig.COMPENSATION_QUEUE
    )
    public void consumeRestaurantEvent(
            RestaurantEvent event) {
    	
    	System.out.println("Compensation Consumer triggered!");
    	
    	System.out.println(
    	        "Received Restaurant Event: "
    	                + event.getRestaurantStatus()
    	);

        if(event.getRestaurantStatus()
                .equals("REJECTED")) {

            compensationService
                    .refundPayment(event);
        }
    }
}