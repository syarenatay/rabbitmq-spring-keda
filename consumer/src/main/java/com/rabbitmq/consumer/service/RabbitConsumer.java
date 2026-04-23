package com.rabbitmq.consumer.service;

import com.rabbitmq.consumer.config.RabbitConfigure;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitConsumer {

    @RabbitListener(queues = RabbitConfigure.QUEUE)
    public void consumeMessage(String message) throws InterruptedException{
        System.out.println("------------------------------------------------");
        System.out.println("Kuyruktan Yeni Mesaj Alındı: " + message);
        System.out.println("------------------------------------------------");
        Thread.sleep(2000);
    }

}
