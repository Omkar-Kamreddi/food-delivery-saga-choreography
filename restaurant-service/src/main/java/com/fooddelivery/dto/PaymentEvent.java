package com.fooddelivery.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent {

    private Long orderId;

    private String paymentStatus;

    private Double amount;
}
