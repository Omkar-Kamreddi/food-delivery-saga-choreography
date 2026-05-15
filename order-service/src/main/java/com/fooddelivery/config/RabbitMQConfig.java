package com.fooddelivery.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

@Configuration
public class RabbitMQConfig {

	//message router
    public static final String EXCHANGE = "food_exchange";

    //Stores messages
    public static final String ORDER_QUEUE = "order_queue";

    //Decides routing
    public static final String ORDER_ROUTING_KEY =
            "order_routing_key";
    
    //compensation work
    public static final String COMPENSATION_QUEUE =
            "order_compensation_queue";

    
    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE);
    }
    
    
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }
    
    
    @Bean
    public Binding binding(
            Queue orderQueue,
            TopicExchange exchange) {

        return BindingBuilder
                .bind(orderQueue)
                .to(exchange)
                .with(ORDER_ROUTING_KEY);
    }
    
    //compensation work
    @Bean
    public Queue compensationQueue() {
        return new Queue(COMPENSATION_QUEUE);
    }
    
    //i want to listen -> Compensation routing key
    @Bean
    public Binding compensationBinding(
            Queue compensationQueue,
            TopicExchange exchange) {

        return BindingBuilder
                .bind(compensationQueue)
                .to(exchange)
                .with("compensation_routing_key");
    }
    
    
 // JSON Converter
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
}