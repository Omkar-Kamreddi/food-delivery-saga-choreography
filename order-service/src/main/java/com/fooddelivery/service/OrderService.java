package com.fooddelivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.dto.OrderEvent;
import com.fooddelivery.entity.FoodOrder;
import com.fooddelivery.producer.OrderProducer;
import com.fooddelivery.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderProducer producer;

    public FoodOrder createOrder(FoodOrder order) {

        // Set initial status
        order.setStatus("PENDING");

        // Save into PostgreSQL
        FoodOrder savedOrder =
                repository.save(order);

        // Create event object
        OrderEvent event = new OrderEvent(
                savedOrder.getId(),
                savedOrder.getItemName(),
                savedOrder.getPrice(),
                savedOrder.getStatus()
        );

        // Send message to RabbitMQ
        producer.sendOrderEvent(event);

        return savedOrder;
    }
}