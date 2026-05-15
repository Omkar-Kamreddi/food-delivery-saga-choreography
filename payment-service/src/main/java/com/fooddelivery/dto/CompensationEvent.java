package com.fooddelivery.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompensationEvent {

    private Long orderId;

    private String message;
}