package com.aniss.userservice;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "user_deleted", groupId = "group_id")
    public void consume(String message) {
        // Handle the message (e.g., log it, update internal state, etc.)
        System.out.println("Received message: " + message);
    }
}
