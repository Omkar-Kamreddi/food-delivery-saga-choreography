package com.fooddelivery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.entity.Payment;

public interface PaymentRepository
        extends JpaRepository<Payment, Long> {
	
	public Optional<Payment> findByOrderId(Long orderId);
}