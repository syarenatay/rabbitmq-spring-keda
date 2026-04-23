package com.rabbitmq.producer.controller;

import com.rabbitmq.producer.config.RabbitConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notification")
@RequiredArgsConstructor
public class ProducerController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping
    public String sendNotification(@RequestBody String message) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.ROUTING_KEY,
                message
        );
        return "Mesaj RabbitMQ kuyruğuna gönderildi: " + message;
    }

    @PostMapping("/1000-message")
    public String sendThousandMessages(@RequestBody String baseMessage) {
        for (int i = 1; i <= 1000; i++) {
            String timedMessage = baseMessage + " - Mesaj No: " + i;

            rabbitTemplate.convertAndSend(
                    RabbitConfig.EXCHANGE,
                    RabbitConfig.ROUTING_KEY,
                    timedMessage
            );
        }
        return "1000 adet mesaj başarıyla fırlatıldı!";
    }


}
