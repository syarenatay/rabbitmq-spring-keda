package com.rabbitmq.consumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfigure {

    public static final String QUEUE = "notification_queue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true);
    }
}
