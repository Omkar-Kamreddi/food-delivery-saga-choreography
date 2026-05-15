package com.fooddelivery.service;

import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.dto.CompensationEvent;
import com.fooddelivery.dto.RestaurantEvent;
import com.fooddelivery.entity.Payment;
import com.fooddelivery.repository.PaymentRepository;

@Service
public class CompensationService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private PaymentRepository repository;

    public void refundPayment(
            RestaurantEvent event) {

        // Find payment by orderId
        Optional<Payment> optionalPayment =
                repository.findByOrderId(
                        event.getOrderId()
                );

        if(optionalPayment.isPresent()) {

            Payment payment =
                    optionalPayment.get();

            // Update payment status
            payment.setPaymentStatus(
                    "REFUNDED"
            );

            repository.save(payment);

            System.out.println(
                    "Payment Refunded For Order ID: "
                            + event.getOrderId()
            );
        }

        // Publish compensation event
        CompensationEvent compensationEvent =
                new CompensationEvent(
                        event.getOrderId(),
                        "PAYMENT_REFUNDED"
                );

        rabbitTemplate.convertAndSend(
                "food_exchange",
                "compensation_routing_key",
                compensationEvent
        );

        System.out.println(
                "Compensation Event Published"
        );
    }
}