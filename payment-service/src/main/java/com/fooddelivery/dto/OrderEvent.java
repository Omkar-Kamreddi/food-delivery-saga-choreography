package com.fooddelivery.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {

    private Long orderId;

    private String itemName;

    private Double price;

    private String status;
}