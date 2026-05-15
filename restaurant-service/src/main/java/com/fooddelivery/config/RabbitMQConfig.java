package com.fooddelivery.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE =
            "food_exchange";

    public static final String RESTAURANT_QUEUE =
            "restaurant_queue";

    public static final String PAYMENT_ROUTING_KEY =
            "payment_routing_key";

    public static final String RESTAURANT_ROUTING_KEY =
            "restaurant_routing_key";

    @Bean
    public Queue restaurantQueue() {
        return new Queue(RESTAURANT_QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(
            Queue restaurantQueue,
            TopicExchange exchange) {

        return BindingBuilder
                .bind(restaurantQueue)
                .to(exchange)
                .with(PAYMENT_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}