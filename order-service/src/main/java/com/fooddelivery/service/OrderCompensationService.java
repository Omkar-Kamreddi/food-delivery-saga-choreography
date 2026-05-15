package com.fooddelivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.entity.FoodOrder;
import com.fooddelivery.repository.OrderRepository;

@Service
public class OrderCompensationService {

    @Autowired
    private OrderRepository repository;

    public void cancelOrder(Long orderId) {

        FoodOrder order =
                repository.findById(orderId)
                        .orElseThrow();

        order.setStatus("CANCELLED");

        repository.save(order);

        System.out.println(
                "Order Cancelled: " + orderId
        );
    }
}