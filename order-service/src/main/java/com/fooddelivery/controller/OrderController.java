package com.fooddelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fooddelivery.entity.FoodOrder;
import com.fooddelivery.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public FoodOrder createOrder(
            @RequestBody FoodOrder order) {

        return service.createOrder(order);
    }
}