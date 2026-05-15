package com.fooddelivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.dto.OrderEvent;
import com.fooddelivery.dto.PaymentEvent;
import com.fooddelivery.entity.Payment;
import com.fooddelivery.producer.PaymentProducer;
import com.fooddelivery.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private PaymentProducer producer;

    public void processPayment(OrderEvent orderEvent) {

        // Simulate payment logic
        boolean paymentSuccess = true;

        Payment payment = new Payment();

        payment.setOrderId(orderEvent.getOrderId());
        payment.setAmount(orderEvent.getPrice());

        if(paymentSuccess) {

            payment.setPaymentStatus("SUCCESS");

        } else {

            payment.setPaymentStatus("FAILED");
        }

        repository.save(payment);

        // Publish payment result
        PaymentEvent paymentEvent =
                new PaymentEvent(
                        orderEvent.getOrderId(),
                        payment.getPaymentStatus(),
                        payment.getAmount()
                );

        producer.sendPaymentEvent(paymentEvent);

        System.out.println(
                "Payment processed for Order ID: "
                        + orderEvent.getOrderId()
        );
    }
}