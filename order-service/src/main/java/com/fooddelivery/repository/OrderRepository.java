package com.fooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.entity.FoodOrder;

public interface OrderRepository
        extends JpaRepository<FoodOrder, Long> {
}