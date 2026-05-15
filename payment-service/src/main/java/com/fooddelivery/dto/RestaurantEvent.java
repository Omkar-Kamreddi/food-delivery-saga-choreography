package com.fooddelivery.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEvent {

    private Long orderId;

    private String restaurantStatus;
}