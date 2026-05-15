package com.fooddelivery.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "food_exchange";

    public static final String ORDER_QUEUE = "order_queue";

    public static final String PAYMENT_QUEUE =
            "payment_queue";
    
    
    //Compensation work 
    public static final String COMPENSATION_QUEUE =
            "compensation_queue";

    public static final String COMPENSATION_ROUTING_KEY =
            "compensation_routing_key";
    

    //Payment service listens to:
    public static final String ORDER_ROUTING_KEY =
            "order_routing_key";

    public static final String PAYMENT_ROUTING_KEY =
            "payment_routing_key";

    @Bean
    public Queue paymentQueue() {
        return new Queue(PAYMENT_QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding paymentBinding(
            Queue paymentQueue,
            TopicExchange exchange) {

        return BindingBuilder
                .bind(paymentQueue)
                .to(exchange)
                .with(ORDER_ROUTING_KEY);
    }
    
    
    //handling compensation work
    @Bean
    public Queue compensationQueue() {
        return new Queue(COMPENSATION_QUEUE);
    }
    
  //i want to listen -> restaurant routing key
    //Payment service now listens for -> restaurant_routing_key
    @Bean
    public Binding compensationBinding(
            Queue compensationQueue,
            TopicExchange exchange) {

        return BindingBuilder
                .bind(compensationQueue)
                .to(exchange)
                .with("restaurant_routing_key");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}