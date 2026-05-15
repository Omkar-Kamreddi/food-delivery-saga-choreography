package com.fooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.entity.RestaurantOrder;

public interface RestaurantRepository
        extends JpaRepository<RestaurantOrder, Long> {
}