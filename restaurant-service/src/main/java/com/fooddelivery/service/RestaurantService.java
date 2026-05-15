package com.fooddelivery.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.dto.PaymentEvent;
import com.fooddelivery.dto.RestaurantEvent;
import com.fooddelivery.entity.RestaurantOrder;
import com.fooddelivery.producer.RestaurantProducer;
import com.fooddelivery.repository.RestaurantRepository;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private RestaurantProducer producer;

    public void processRestaurantOrder(
            PaymentEvent paymentEvent) {

        // Simulate restaurant logic
//        boolean restaurantAccepted = false;
    	boolean restaurantAccepted =
    	        new Random().nextBoolean();

        RestaurantOrder order =
                new RestaurantOrder();

        order.setOrderId(
                paymentEvent.getOrderId()
        );

        if(restaurantAccepted) {

            order.setRestaurantStatus(
                    "ACCEPTED"
            );

        } else {

            order.setRestaurantStatus(
                    "REJECTED"
            );
        }

        repository.save(order);

        System.out.println(
        	    "Publishing Restaurant Event: "
        	    + order.getRestaurantStatus()
        	);
        
        // Publish restaurant event
        RestaurantEvent event =
                new RestaurantEvent(
                        paymentEvent.getOrderId(),
                        order.getRestaurantStatus()
                );

        producer.sendRestaurantEvent(event);

        System.out.println(
                "Restaurant processed order: "
                        + paymentEvent.getOrderId()
        );
    }
}
